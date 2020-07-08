package com.github.blog.utils

import com.github.blog.entity.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import javax.crypto.SecretKey


const val SECRET_KEY = "WKC5t/3NUMyDC+jDI+U2CFuctATORDZc0I9YLI4zCBA="
val key: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY))

fun createJWT(user: User): String {
    return Jwts.builder()
            .claim("id", user.id.toString()).signWith(key).compact()
}

fun getUserIdFromJWT(jws: String): Long {
    val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).body
    val idStr = claims["id"] as String
    return idStr.toLong()
}