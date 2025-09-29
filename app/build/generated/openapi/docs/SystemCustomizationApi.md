# SystemCustomizationApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**systemLockscreenConfigGet**](SystemCustomizationApi.md#systemLockscreenConfigGet) | **GET** /system/lockscreen-config | Get lock screen configuration |
| [**systemLockscreenConfigPut**](SystemCustomizationApi.md#systemLockscreenConfigPut) | **PUT** /system/lockscreen-config | Update lock screen configuration |


<a id="systemLockscreenConfigGet"></a>
# **systemLockscreenConfigGet**
> LockScreenConfig systemLockscreenConfigGet()

Get lock screen configuration

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import dev.aurakai.auraframefx.openapi.model.*

val apiInstance = SystemCustomizationApi()
try {
    val result : LockScreenConfig = apiInstance.systemLockscreenConfigGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SystemCustomizationApi#systemLockscreenConfigGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SystemCustomizationApi#systemLockscreenConfigGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**LockScreenConfig**](LockScreenConfig.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="systemLockscreenConfigPut"></a>
# **systemLockscreenConfigPut**
> systemLockscreenConfigPut(lockScreenConfig)

Update lock screen configuration

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import dev.aurakai.auraframefx.openapi.model.*

val apiInstance = SystemCustomizationApi()
val lockScreenConfig : LockScreenConfig =  // LockScreenConfig | 
try {
    apiInstance.systemLockscreenConfigPut(lockScreenConfig)
} catch (e: ClientException) {
    println("4xx response calling SystemCustomizationApi#systemLockscreenConfigPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SystemCustomizationApi#systemLockscreenConfigPut")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **lockScreenConfig** | [**LockScreenConfig**](LockScreenConfig.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

