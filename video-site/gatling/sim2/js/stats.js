var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "100",
        "ok": "100",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "2449",
        "ok": "2449",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "13443",
        "ok": "13443",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "6823",
        "ok": "6823",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "2097",
        "ok": "2097",
        "ko": "-"
    },
    "percentiles1": {
        "total": "6387",
        "ok": "6387",
        "ko": "-"
    },
    "percentiles2": {
        "total": "8300",
        "ok": "8300",
        "ko": "-"
    },
    "percentiles3": {
        "total": "10286",
        "ok": "10286",
        "ko": "-"
    },
    "percentiles4": {
        "total": "13412",
        "ok": "13412",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 100,
    "percentage": 100
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "5.556",
        "ok": "5.556",
        "ko": "-"
    }
},
contents: {
"req_get-480p-qualit-09730": {
        type: "REQUEST",
        name: "Get 480p quality",
path: "Get 480p quality",
pathFormatted: "req_get-480p-qualit-09730",
stats: {
    "name": "Get 480p quality",
    "numberOfRequests": {
        "total": "50",
        "ok": "50",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "2449",
        "ok": "2449",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "13443",
        "ok": "13443",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "7068",
        "ok": "7068",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "2505",
        "ok": "2505",
        "ko": "-"
    },
    "percentiles1": {
        "total": "6325",
        "ok": "6325",
        "ko": "-"
    },
    "percentiles2": {
        "total": "9024",
        "ok": "9024",
        "ko": "-"
    },
    "percentiles3": {
        "total": "10457",
        "ok": "10457",
        "ko": "-"
    },
    "percentiles4": {
        "total": "13428",
        "ok": "13428",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 50,
    "percentage": 100
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.778",
        "ok": "2.778",
        "ko": "-"
    }
}
    },"req_get-origin-qual-a0328": {
        type: "REQUEST",
        name: "Get origin quality",
path: "Get origin quality",
pathFormatted: "req_get-origin-qual-a0328",
stats: {
    "name": "Get origin quality",
    "numberOfRequests": {
        "total": "50",
        "ok": "50",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "3605",
        "ok": "3605",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "10298",
        "ok": "10298",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "6579",
        "ok": "6579",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1550",
        "ok": "1550",
        "ko": "-"
    },
    "percentiles1": {
        "total": "6498",
        "ok": "6498",
        "ko": "-"
    },
    "percentiles2": {
        "total": "7901",
        "ok": "7901",
        "ko": "-"
    },
    "percentiles3": {
        "total": "8954",
        "ok": "8954",
        "ko": "-"
    },
    "percentiles4": {
        "total": "10039",
        "ok": "10039",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 50,
    "percentage": 100
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.778",
        "ok": "2.778",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
