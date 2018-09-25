# jwtauth
Spring Security JWT Authentication – RestAPIs SpringBoot + Spring MVC + Spring JPA + MySQL

Technologies
– Spring Boot
– jjwt – 0.9.0
– Spring Security
– Spring JPA
– MySQL

JSON Web Token
JSON Web Token (JWT) defines a compact and self-contained way for securely transmitting information between parties as a JSON object.

Scenarios where JSON Web Tokens are useful:

Authorization: the most common scenario for using JWT. Single Sign On is a feature that widely uses JWT
Information Exchange: Because JWTs can be signed, JSON Web Tokens are a good way of securely transmitting information between parties.
JSON Web Tokens consist of 3 parts:

Header
Payload
Signature
-> JWT looks like Header-Base64-String.Payload-Base64-String.Signature-Base64-String

Header	consists of two parts:

token type.
hashing algorithm.
-> Example:


{
  "alg": "HS256",
  "typ": "JWT"
}


Payload contains the claims. Claims are statements about an entity and additional information.
There are 3 types of claims ->

Registered claims -> These are a set of predefined claims: iss (issuer), exp (expiration time), sub (subject)
Public claims
Private claims
Example ->


{
  "sub": "thomasgkz",
  "iat": 1537603195,
  "exp": 1537689595
}

Signature -> To create the signature part you have to take the encoded header, the encoded payload, a secret, the algorithm specified in the header, and sign that.

Example ->


HMACSHA512(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  your-256-bit-secret
)

Combine all together, we get 3 Base64-URL strings separated by dots,

-> Example:


eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aG9tYXNna3oiLCJpYXQiOjE1Mzc2MDMxOTUsImV4cCI6MTUzNzY4OTU5NX0.m2YMjTYmOnfR7nnVNxqCzWbQ2FhKRe1eiizxnC2TF4eAoEzKlwo7PheVkKcxj08ST3vB-ZOIhiORvYVfSgzcog

When accessing a protected route or resource, the user agent should send the JWT, typically in the Authorization header using the Bearer schema.

-> Example:


Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aG9tYXNna3oiLCJpYXQiOjE1Mzc2MDMxOTUsImV4cCI6MTUzNzY4OTU5NX0.m2YMjTYmOnfR7nnVNxqCzWbQ2FhKRe1eiizxnC2TF4eAoEzKlwo7PheVkKcxj08ST3vB-ZOIhiORvYVfSgzcog

See more at: https://jwt.io/introduction/
