package br.com.lucasladeira.mercadolivro.security

import br.com.lucasladeira.mercadolivro.exceptions.model.AuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.config.annotation.rsocket.RSocketSecurity.JwtSpec
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(id: String): String{
        return Jwts
            .builder()
            .setSubject(id)
            .setExpiration(Date(System.currentTimeMillis() + expiration!!))
            .signWith(SignatureAlgorithm.HS512, secret!!.toByteArray())
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        val claims = getClaims(token)
        if (claims.subject == null || claims.expiration == null || Date().after(claims.expiration)){
            return false
        }
        return true
    }

    private fun getClaims(token: String): Claims {
        try{
            return Jwts.parser()
                .setSigningKey(secret!!.toByteArray())
                .parseClaimsJws(token).body
        }catch (ex: Exception){
            throw AuthenticationException("Token inválido!", "312312")
        }
    }

    fun getSubject(token: String): String {
        return getClaims(token).subject
    }
}