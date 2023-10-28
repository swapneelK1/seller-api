The task is to design a graphql api for an application which shows aggregated seller information for different companies. 

Query: 
``` bash
query {
  sellers(filter: {searchByName:"amazon1", producerIds:[], marketplaceIds:[]}, page: {page: 0, size:10}, sortBy: SELLER_INFO_EXTERNAL_ID_ASC) {
    meta {
      page
      size
      totalPage
    }
    data{
      sellerName
      externalId
      marketplaceId
      producerSellerStates {
        producerId
        producerName
        sellerState
        sellerId
      }
    }
  }
}
```

Sample Response:
``` bash
{
  "data": {
    "sellers": {
      "meta": {
        "page": 0,
        "size": 3,
        "totalPage": 1
      },
      "data": [
        {
          "sellerName": "amazon1",
          "externalId": "A2QUTRSO1ZHRN9",
          "marketplaceId": "amazon",
          "producerSellerStates": [
            {
              "producerId": "870eb3e0-6a28-46fd-a763-0f8b47aa8034",
              "producerName": "Nike",
              "sellerState": "BLACKLISTED",
              "sellerId": "0c4cd951-0256-4d5e-95a6-51a7c8c5174f"
            },
            {
              "producerId": "6b2520b1-3695-450b-9e66-39ec4356a97c",
              "producerName": "adidas",
              "sellerState": "WHITELISTED",
              "sellerId": "057cd7ba-4088-4ff1-be69-c4f4847ed8a8"
            }
          ]
        }
      ]
    }
  }
}
```
