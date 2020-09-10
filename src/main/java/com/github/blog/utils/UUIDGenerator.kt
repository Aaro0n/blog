package com.github.blog.utils

import org.hibernate.HibernateException
import org.hibernate.MappingException
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService
import org.hibernate.boot.registry.classloading.spi.ClassLoadingException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.Configurable
import org.hibernate.id.IdentifierGenerator
import org.hibernate.id.UUIDGenerationStrategy
import org.hibernate.id.uuid.StandardRandomStrategy
import org.hibernate.internal.CoreLogging
import org.hibernate.service.ServiceRegistry
import org.hibernate.type.Type
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor
import java.io.Serializable
import java.util.*


/**
 * An [IdentifierGenerator] which generates [UUID] values using a pluggable
 * [generation strategy][UUIDGenerationStrategy].  The values this generator can return
 * include [UUID], [String] and byte[16]
 *
 *
 * Supports 2 config parameters:
 *  * [.UUID_GEN_STRATEGY] - names the [UUIDGenerationStrategy] instance to use
 *  * [.UUID_GEN_STRATEGY_CLASS] - names the [UUIDGenerationStrategy] class to use
 *
 *
 *
 * Currently there are 2 standard implementations of [UUIDGenerationStrategy]:
 *  * [StandardRandomStrategy] (the default, if none specified)
 *  * [org.hibernate.id.uuid.CustomVersionOneStrategy]
 *
 *
 * @author Steve Ebersole
 */
class UUIDGenerator : IdentifierGenerator, Configurable {
    private var strategy: UUIDGenerationStrategy? = null
    private var valueTransformer: UUIDTypeDescriptor.ValueTransformer? = null

    @Throws(MappingException::class)
    override fun configure(type: Type, params: Properties, serviceRegistry: ServiceRegistry) {
        // check first for the strategy instance
        strategy = params[UUID_GEN_STRATEGY] as UUIDGenerationStrategy?
        if (strategy == null) {
            // next check for the strategy class
            val strategyClassName = params.getProperty(UUID_GEN_STRATEGY_CLASS)
            if (strategyClassName != null) {
                try {
                    val cls = serviceRegistry.getService(ClassLoaderService::class.java)
                    val strategyClass: Class<*> = cls.classForName<Any>(strategyClassName)
                    try {
                        strategy = strategyClass.newInstance() as UUIDGenerationStrategy
                    } catch (ignore: Exception) {
                        LOG.unableToInstantiateUuidGenerationStrategy(ignore)
                    }
                } catch (ignore: ClassLoadingException) {
                    LOG.unableToLocateUuidGenerationStrategy(strategyClassName)
                }
            }
        }
        if (strategy == null) {
            // lastly use the standard random generator
            strategy = StandardRandomStrategy.INSTANCE
        }
        valueTransformer = when {
            UUID::class.java.isAssignableFrom(type.returnedClass) -> {
                UUIDTypeDescriptor.PassThroughTransformer.INSTANCE
            }
            String::class.java.isAssignableFrom(type.returnedClass) -> {
                UUIDTypeDescriptor.ToStringTransformer.INSTANCE
            }
            ByteArray::class.java.isAssignableFrom(type.returnedClass) -> {
                UUIDTypeDescriptor.ToBytesTransformer.INSTANCE
            }
            else -> {
                throw HibernateException("Unanticipated return type [" + type.returnedClass.name + "] for UUID conversion")
            }
        }
    }

    @Throws(HibernateException::class)
    override fun generate(session: SharedSessionContractImplementor, `object`: Any): Serializable {
        return valueTransformer!!.transform(strategy!!.generateUUID(session)).toString().replace("-", "").slice(0..7)
    }

    companion object {
        const val UUID_GEN_STRATEGY = "uuid_gen_strategy"
        const val UUID_GEN_STRATEGY_CLASS = "uuid_gen_strategy_class"
        private val LOG = CoreLogging.messageLogger(UUIDGenerator::class.java)
        fun buildSessionFactoryUniqueIdentifierGenerator(): UUIDGenerator {
            val generator = UUIDGenerator()
            generator.strategy = StandardRandomStrategy.INSTANCE
            generator.valueTransformer = UUIDTypeDescriptor.ToStringTransformer.INSTANCE
            return generator
        }
    }
}