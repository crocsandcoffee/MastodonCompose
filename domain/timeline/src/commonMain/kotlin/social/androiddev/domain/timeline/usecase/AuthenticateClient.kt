package social.androiddev.domain.timeline.usecase

class AuthenticateClient() {

    /**
     *
     */
    suspend operator fun invoke(
        domain: String,
        clientName: String,
        redirectURIs: String,
        scopes: String = "read write follow push",
        website: String? = null,
    ) {

    }
}