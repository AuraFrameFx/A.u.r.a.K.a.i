# TasksApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**tasksSchedulePost**](TasksApi.md#tasksSchedulePost) | **POST** /tasks/schedule | Schedule a new task |
| [**tasksTaskIdGet**](TasksApi.md#tasksTaskIdGet) | **GET** /tasks/{taskId} | Get task status |


<a id="tasksSchedulePost"></a>
# **tasksSchedulePost**
> TaskStatus tasksSchedulePost(taskScheduleRequest)

Schedule a new task

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import dev.aurakai.auraframefx.openapi.model.*

val apiInstance = TasksApi()
val taskScheduleRequest : TaskScheduleRequest =  // TaskScheduleRequest | 
try {
    val result : TaskStatus = apiInstance.tasksSchedulePost(taskScheduleRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TasksApi#tasksSchedulePost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TasksApi#tasksSchedulePost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **taskScheduleRequest** | [**TaskScheduleRequest**](TaskScheduleRequest.md)|  | |

### Return type

[**TaskStatus**](TaskStatus.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="tasksTaskIdGet"></a>
# **tasksTaskIdGet**
> TaskStatus tasksTaskIdGet(taskId)

Get task status

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import dev.aurakai.auraframefx.openapi.model.*

val apiInstance = TasksApi()
val taskId : kotlin.String = taskId_example // kotlin.String | ID of the task to check
try {
    val result : TaskStatus = apiInstance.tasksTaskIdGet(taskId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TasksApi#tasksTaskIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TasksApi#tasksTaskIdGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **taskId** | **kotlin.String**| ID of the task to check | |

### Return type

[**TaskStatus**](TaskStatus.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

