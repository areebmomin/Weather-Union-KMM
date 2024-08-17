package com.areeb.weatherunion.core.network

sealed class ApiResponse<out Response> {
    data class Success<Response>(val body: Response) : ApiResponse<Response>()

    sealed class Error : ApiResponse<Nothing>() {
        data class HttpError(
            val code: Int,
            val errorBody: String?,
            val errorMessage: String?,
        ) : Error()

        data class SerializationError(
            val message: String?,
            val errorMessage: String?,
        ) : Error()

        data class GenericError(
            val message: String?,
            val errorMessage: String?,
        ) : Error()
    }
}

fun <Response, Model> ApiResponse<Response>.mapSuccess(
    transform: (Response) -> Model,
): ApiResponse<Model> {
    return when (this) {
        is ApiResponse.Success -> ApiResponse.Success(transform(body))
        is ApiResponse.Error -> this
    }
}
