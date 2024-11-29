# Money Exchange Project

## API Endpoints

## 1. User

<details>
<summary style="font-size: 1.17em; font-weight: bold;">유저 생성</summary>
<div markdown="1">

| **이름**   | **타입**               | **설명**  | **필수여부** |
|----------|----------------------|---------| --- |
| id       | INT (AUTO_INCREMENT) | 유저 id   | Y |
| name     | VARCHAR(10)          | 유저 이름   | Y |
| email    | VARCHAR(255)         | 유저 이메일  | Y |

### Request

```
POST 'localhost:8080/users'
```

```json
{
  "name": "user1",
  "email": "user1@test.com"
}
```

### Response

Success HTTP Status : 201

``` json
{
    "id": 1,
    "name": "user1",
    "email": "user1@test.com"
}
```

</div>
</details>

<details>
<summary style="font-size: 1.17em; font-weight: bold;">유저 전체 조회</summary>
<div markdown="1">


| **이름**   | **타입**               | **설명**  | **필수여부** |
|----------|----------------------|---------| --- |
| id       | INT (AUTO_INCREMENT) | 유저 id   | Y |
| name     | VARCHAR(10)          | 유저 이름   | Y |
| email    | VARCHAR(40)          | 유저 이메일  | Y |

### Request

```
GET 'localhost:8080/users'
```

### Response

Success HTTP Status : 200

```json
[
    {
        "id": 1,
        "name": "Park",
        "email": "bbb@gmail.com"
    },
    {
        "id": 2,
        "name": "Kim",
        "email": "kim@gmail.com"
    }
]
```

유저가 없는 경우 빈 배열 반환

```json
[]
```

</div>
</details>

<details>
<summary style="font-size: 1.17em; font-weight: bold;">유저 선택 조회</summary>
<div markdown="1">


| **이름**   | **타입**               | **설명**  | **필수여부** |
|----------|----------------------|---------| --- |
| id       | INT (AUTO_INCREMENT) | 유저 id   | Y |
| name     | VARCHAR(10)          | 유저 이름   | Y |
| email    | VARCHAR(40)          | 유저 이메일  | Y |

### Request

```
GET 'localhost:8080/users/{id}'
```

### Response

Success HTTP Status : 200

Fail HTTP Status : 404

성공

``` json
{
    "id": 1,
    "name": "Park",
    "email": "bbb@gmail.com"
}
```

실패

``` json
{
    "httpStatus": "NOT_FOUND",
    "message": "사용자를 찾을 수 없습니다."
}
```

</div>
</details>

<details>
<summary style="font-size: 1.17em; font-weight: bold;">유저 삭제</summary>
<div markdown="1">

### Request

```
DELETE 'localhost:8080/users/{id}'
```

### Response

Success HTTP Status : 200

Fail HTTP Status : 404

성공

``` plaintext
정상적으로 삭제되었습니다.
```

실패

``` json
{
    "httpStatus": "NOT_FOUND",
    "message": "사용자를 찾을 수 없습니다."
}
```

</div>
</details>

----

## 2. Currency

<details>
<summary style="font-size: 1.17em; font-weight: bold;">통화 생성</summary>
<div markdown="1">

| **이름**       | **타입**               | **설명** | **필수여부** |
|--------------|----------------------|--------| --- |
| id           | INT (AUTO_INCREMENT) | 통화 id  | Y |
| currencyName | VARCHAR(10)          | 통화 이름  | Y |
| exchangeRate | DECIMAL(38,2)        | 통화 환율  | Y |
| symbol       | VARCHAR(3)           | 통화 심볼  | Y |

### Request

```
POST 'http://localhost:8080/currencies'
```

```json
{
  "currencyName": "USD",
  "exchangeRate": 1395.00,
  "symbol": "$"
}
```

### Response

Success HTTP Status : 201

Fail HTTP Status : 400 (중복된 통화를 생성할 시)

성공
```json
{
    "id": 1,
    "currencyName": "USD",
    "exchangeRate": 1395.00,
    "symbol": "$"
}
```

실패
```json
{
    "httpStatus": "BAD_REQUEST",
    "message": "이미 같은 통화가 존재합니다."
}
```

</div>
</details>

<details>
<summary style="font-size: 1.17em; font-weight: bold;">통화 전체 조회</summary>
<div markdown="1">

| **이름**       | **타입**               | **설명** | **필수여부** |
|--------------|----------------------|--------| --- |
| id           | INT (AUTO_INCREMENT) | 통화 id  | Y |
| currencyName | VARCHAR(10)          | 통화 이름  | Y |
| exchangeRate | DECIMAL(38,2)        | 통화 환율  | Y |
| symbol       | VARCHAR(3)           | 통화 심볼  | Y |

### Request

```
GET 'http://localhost:8080/currencies'
```

### Response

Success HTTP Status : 200

```json
[
    {
        "id": 1,
        "currencyName": "USD",
        "exchangeRate": 1395.00,
        "symbol": "$"
    },
    {
        "id": 2,
        "currencyName": "KRW",
        "exchangeRate": 1000.00,
        "symbol": "₩"
    }
]
```

통화가 없는 경우 빈 배열 반환

```json
[]
```

</div>
</details>

<details>
<summary style="font-size: 1.17em; font-weight: bold;">통화 선택 조회</summary>
<div markdown="1">

| **이름**       | **타입**               | **설명** | **필수여부** |
|--------------|----------------------|--------| --- |
| id           | INT (AUTO_INCREMENT) | 통화 id  | Y |
| currencyName | VARCHAR(10)          | 통화 이름  | Y |
| exchangeRate | DECIMAL(38,2)        | 통화 환율  | Y |
| symbol       | VARCHAR(3)           | 통화 심볼  | Y |

### Request

```
GET 'http://localhost:8080/currencies/{id}'
```

### Response

Success HTTP Status : 200

Fail HTTP Status : 404

성공

```json
{
    "id": 1,
    "currencyName": "USD",
    "exchangeRate": 1395.00,
    "symbol": "$"
}
```

실패

```json
{
    "httpStatus": "NOT_FOUND",
    "message": "통화를 찾을 수 없습니다."
}
```

</div>
</details>

----

## 3. Money Exchange

<details>
<summary style="font-size: 1.17em; font-weight: bold;">환전 요청</summary>
<div markdown="1">

### Request
| **이름**         | **타입**        | **설명**        | **필수여부** |
|----------------|---------------|---------------| --- |
| exchangeAmount | DECIMAL(38,2) | 환전 요청 금액(KRW) | Y |
| currencyName   | VARCHAR(10)   | 통화 이름         | Y |

```
POST 'http://localhost:8080/exchange/{userid}'
```

```json
{
  "exchangeAmount": 50000,
  "currencyName": "USD"
}
```

### Response
| **이름**              | **타입**               | **설명**                      | **필수여부** |
|---------------------|----------------------|-----------------------------| --- |
| id                  | INT (AUTO_INCREMENT) | 환전 id                       | Y |
| name                | VARCHAR(10)          | 유저 이름                       | Y |
| currencyName        | VARCHAR(10)          | 통화 이름                       | Y |
| amountInKrw         | DECIMAL(38,2)        | 환전 요청 금액(KRW)               | Y |
| amountAfterExchange | DECIMAL(38,2)        | 환전 후 금액(환전 된 통화 기준)         | Y |
| exchangeRequestStatus        | TINYINT              | 환전 요청 상태(NORMAL, CANCELLED) | Y |
| createdDate        | DATETIME             | 작성일                         | Y |
| modDate        | DATETIME             | 수정일                         | Y |

Success HTTP Status : 200

Fail HTTP Status : 404

성공
```json
{
    "id": 1,
    "name": "user1",
    "currencyName": "USD",
    "amountInKrw": 50000,
    "amountAfterExchange": 35.84,
    "exchangeRequestStatus": "NORMAL",
    "createdDate": "2024-11-29T10:08:13.80103",
    "modDate": "2024-11-29T10:08:13.80103"
}
```

실패
```json
{
    "httpStatus": "NOT_FOUND",
    "message": "사용자를 찾을 수 없습니다."
}
```

```json
{
    "httpStatus": "NOT_FOUND",
    "message": "통화를 찾을 수 없습니다."
}
```

</div>
</details>

<details>
<summary style="font-size: 1.17em; font-weight: bold;">환전 요청 조회</summary>
<div markdown="1">

| **이름**              | **타입**               | **설명**                      | **필수여부** |
|---------------------|----------------------|-----------------------------| --- |
| id                  | INT (AUTO_INCREMENT) | 환전 id                       | Y |
| name                | VARCHAR(10)          | 유저 이름                       | Y |
| currencyName        | VARCHAR(10)          | 통화 이름                       | Y |
| amountInKrw         | DECIMAL(38,2)        | 환전 요청 금액(KRW)               | Y |
| amountAfterExchange | DECIMAL(38,2)        | 환전 후 금액(환전 된 통화 기준)         | Y |
| exchangeRequestStatus        | TINYINT              | 환전 요청 상태(NORMAL, CANCELLED) | Y |
| createdDate        | DATETIME             | 작성일                         | Y |
| modDate        | DATETIME             | 수정일                         | Y |

### Request

```
GET 'http://localhost:8080/exchange/{userId}'
```

### Response

Success HTTP Status : 200

```json
[
    {
        "id": 1,
        "name": "user1",
        "currencyName": "USD",
        "amountInKrw": 50000.00,
        "amountAfterExchange": 35.84,
        "exchangeRequestStatus": "NORMAL",
        "createdDate": "2024-11-29T10:08:13.80103",
        "modDate": "2024-11-29T10:08:13.80103"
    },
    {
        "id": 2,
        "name": "user1",
        "currencyName": "USD",
        "amountInKrw": 50000.00,
        "amountAfterExchange": 35.84,
        "exchangeRequestStatus": "NORMAL",
        "createdDate": "2024-11-29T10:25:31.667909",
        "modDate": "2024-11-29T10:25:31.667909"
    }
]
```

환전 요청 데이터가 없을 시 빈 배열 반환
```json
[]
```

</div>
</details>

<details>
<summary style="font-size: 1.17em; font-weight: bold;">환전 요청 변경</summary>
<div markdown="1">

| **이름**              | **타입**               | **설명**                      | **필수여부** |
|---------------------|----------------------|-----------------------------| --- |
| id                  | INT (AUTO_INCREMENT) | 환전 id                       | Y |
| name                | VARCHAR(10)          | 유저 이름                       | Y |
| currencyName        | VARCHAR(10)          | 통화 이름                       | Y |
| amountInKrw         | DECIMAL(38,2)        | 환전 요청 금액(KRW)               | Y |
| amountAfterExchange | DECIMAL(38,2)        | 환전 후 금액(환전 된 통화 기준)         | Y |
| exchangeRequestStatus        | TINYINT              | 환전 요청 상태(NORMAL, CANCELLED) | Y |
| createdDate        | DATETIME             | 작성일                         | Y |
| modDate        | DATETIME             | 수정일                         | Y |

### Request

```
PATCH 'http://localhost:8080/exchange/{id}'
```

환전 요청 취소
```json
{
  "exchangeRequestStatus": "CANCELLED"
}
```
환전 요청 정상
```json
{
  "exchangeRequestStatus": "NORMAL"
}
```

### Response

Success HTTP Status : 200

Fail HTTP Status : 400

성공

```json
{
    "id": 1,
    "name": "user1",
    "currencyName": "USD",
    "amountInKrw": 50000.00,
    "amountAfterExchange": 35.84,
    "exchangeRequestStatus": "CANCELLED",
    "createdDate": "2024-11-29T10:08:13.80103",
    "modDate": "2024-11-29T10:30:58.50652"
}
```

실패

```json
{
    "timestamp": "2024-11-29T01:32:55.344+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "JSON parse error: Cannot deserialize value of type `com.jhpark.moneyexchange.ExchangeRequestStatus` from String \"cd\": not one of the values accepted for Enum class: [CANCELLED, NORMAL]",
    "path": "/exchange/1"
}
```

</div>
</details>


## ERD
![](https://velog.velcdn.com/images/jelog_131/post/ad222e68-e799-4a8f-8f35-d0ec2f96f6f8/image.png)

---

## 트러블 슈팅
1. 통화 생성 중 null 값 리턴
2. DB에 중복된 저장 방지하기

- https://buly.kr/15Noina