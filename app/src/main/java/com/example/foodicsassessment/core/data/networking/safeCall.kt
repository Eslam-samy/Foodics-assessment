package com.example.foodicsassessment.core.data.networking

import com.example.foodicsassessment.core.domain.util.NetworkError
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext
import com.example.foodicsassessment.core.domain.util.Result
/*
* this method is used to create http requests and return the errors according to its state
* */

suspend inline fun <reified T> SafeCall(
    excute: () -> HttpResponse,
): Result<T, NetworkError> {
    val response = try {
        excute()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION_ERROR)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }
    return responseToResult(response)
}