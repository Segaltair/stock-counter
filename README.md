# stock-counter
java-приложение для подсчета текущей стоимости портфеля акций и их распределение по секторам.
-
Подключить lombok к проекту.

Запустить main class:

    com.surakin.stock.counter.StartStockCounter

Отправить post запрос на: 

    http://localhost:8080/portfolio

Названия акций можно взять здесь: 

    https://iextrading.com/apps/stocks/
    
Пример запроса:

    {
        "stocks":[
          {
             "symbol":"AAPL",
             "volume":50
          },
          {
             "symbol":"HOG",
             "volume":10
          },
          {
             "symbol":"MDSO",
             "volume":1
          },
          {
             "symbol":"IDRA",
             "volume":1
          },
          {
             "symbol":"MRSN",
             "volume":1
          }
        ]
    }

Пример ответа:

    {
      "value": 10650.09,
      "allocations": [
        {
          "sector": "Technology",
          "assetValue": 10232.71,
          "proportion": 0.961
        },
        {
          "sector": "Healthcare",
          "assetValue": 6.98,
          "proportion": 0.001
        },
        {
          "sector": "Consumer Cyclical",
          "assetValue": 410.4,
          "proportion": 0.039
        }
      ]
    }