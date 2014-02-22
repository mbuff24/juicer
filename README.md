juicer
==============

Twitter stream / ORM / Caching 

To run (for now) you must have twitter oauth credentials and a mysql db

Add a file name properties.json to the root of the project and fill in your credentials below. You must include at least one tracking term to filtering tweets.

```
{
	"db": {
		"url": "",
		"user": "",
		"pw": ""
		
	},
	"twitter": {
		"consumerKey": "",
		"consumerSecret": "",
		"token": "",
		"secret": ""
	},
	"trackingTerms": [
		"",
		"",
    ...
	]
}
```

###Filter Stream

Based off examples from Twitter's Hosebird Client (https://github.com/twitter/hbc)

###Twitter NLP

Uses tools provided by CMU ARK team (https://github.com/brendano/ark-tweet-nlp)
