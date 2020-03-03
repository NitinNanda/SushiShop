# SushiShop
This is a backend project built using the following frameworks/libraries/tools:
• Spring boot
• H2 database(in memory)
• Maven
The API's accept and returns JSON data.
The server starts on port 9000

There are 5 endpoints of this project
GET: /api/orders/status
POST: /api/orders
  Accepts a RequestBody of format
   {
	    "sushi_name": "California Roll"
   }
PUT: /api/orders/pause/{order_id}
PUT: /api/orders/resume/{order_id}
PUT: /api/orders/cancel/{order_id}


There are 3 types of sushi in the in-memory DB along with their processing time.
1. California Roll, 30
2. Kamikaze Roll, 40
3. Dragon Eye, 50

Only 3 orders can be processed at a time.

The Pause and resume functions do not work at the moment.
