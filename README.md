User Service

@Autowired userRespository, tokenService



Generating a Token

saveUser(){
savedUser = userRepository.save(User)

	// creating a token 
        return  tokenService.createToken(savedUser.getId())

}

// token Service
createToken(ObjectId userId){

	TOKEN_SECRET = “sdffdsafdkjkasjffdjsalkf”
	Algorithm // jwt => jwt.HMAC256(TOKEN_SECRET)

	// Generation of JSON Web Token by two claims with userId and Current Date
	String token = JWT.create().withClaim(“useId”,userId.toString()).withClaim(“createdAt”, new Date()).sign(algorithm)

	// return token 


}
