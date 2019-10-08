# stock-counter
java-приложение для подсчета текущей стоимости портфеля акций и их распределение по секторам.
-
Для запуска проекта запустить start.sh  

Отправить post запрос на: 

    http://localhost:8080/portfolio
    
Для такого json:

    {
      "stocks": [
        {
          "symbol": "AAPL",
          "volume": 50
        },
        {
          "symbol": "HOG",
          "volume": 10
        },
    }

Пример запроса будет выглядеть так:

    curl -i -X POST -H "Content-Type:application/json" -H "Cookie:sk_73fe5d5d901248409c8b03e50617efb7" -d "{\"stocks\":[{\"symbol\":\"AAPL\", \"volume\":50}, {\"symbol\":\"HOG\", \"volume\":10}]}" "http://localhost:8080/portfolio"

Пример ответа:

    HTTP/1.1 200
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Thu, 11 Jun 2019 00:00:31 GMT
    
    {"value":15442,"allocations":[{"sector":"Electronic Technology","assetValue":15142,"proportion":0.981},{"sector":"Consumer Durables","assetValue":301,"proportion":0.019}]}
