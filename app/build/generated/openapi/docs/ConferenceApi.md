# ConferenceApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**conferenceRoomsPost**](ConferenceApi.md#conferenceRoomsPost) | **POST** /conference/rooms | Create a new conference room |


<a id="conferenceRoomsPost"></a>
# **conferenceRoomsPost**
> ConferenceRoom conferenceRoomsPost(conferenceRoomCreateRequest)

Create a new conference room

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import dev.aurakai.auraframefx.openapi.model.*

val apiInstance = ConferenceApi()
val conferenceRoomCreateRequest : ConferenceRoomCreateRequest =  // ConferenceRoomCreateRequest | 
try {
    val result : ConferenceRoom = apiInstance.conferenceRoomsPost(conferenceRoomCreateRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ConferenceApi#conferenceRoomsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ConferenceApi#conferenceRoomsPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **conferenceRoomCreateRequest** | [**ConferenceRoomCreateRequest**](ConferenceRoomCreateRequest.md)|  | |

### Return type

[**ConferenceRoom**](ConferenceRoom.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

