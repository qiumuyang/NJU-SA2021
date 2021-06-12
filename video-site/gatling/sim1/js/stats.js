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
        "total": "3615",
        "ok": "3615",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "22371",
        "ok": "22371",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "16648",
        "ok": "16648",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "5373",
        "ok": "5373",
        "ko": "-"
    },
    "percentiles1": {
        "total": "19456",
        "ok": "19456",
        "ko": "-"
    },
    "percentiles2": {
        "total": "20283",
        "ok": "20283",
        "ko": "-"
    },
    "percentiles3": {
        "total": "21300",
        "ok": "21300",
        "ko": "-"
    },
    "percentiles4": {
        "total": "22365",
        "ok": "22365",
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
        "total": "2.381",
        "ok": "2.381",
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
        "total": "3615",
        "ok": "3615",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "22371",
        "ok": "22371",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "13323",
        "ok": "13323",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "5941",
        "ok": "5941",
        "ko": "-"
    },
    "percentiles1": {
        "total": "14016",
        "ok": "14016",
        "ko": "-"
    },
    "percentiles2": {
        "total": "17731",
        "ok": "17731",
        "ko": "-"
    },
    "percentiles3": {
        "total": "22075",
        "ok": "22075",
        "ko": "-"
    },
    "percentiles4": {
        "total": "22368",
        "ok": "22368",
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
        "total": "1.19",
        "ok": "1.19",
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
        "total": "19019",
        "ok": "19019",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "21087",
        "ok": "21087",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "19974",
        "ok": "19974",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "567",
        "ok": "567",
        "ko": "-"
    },
    "percentiles1": {
        "total": "19833",
        "ok": "19833",
        "ko": "-"
    },
    "percentiles2": {
        "total": "20395",
        "ok": "20395",
        "ko": "-"
    },
    "percentiles3": {
        "total": "20930",
        "ok": "20930",
        "ko": "-"
    },
    "percentiles4": {
        "total": "21077",
        "ok": "21077",
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
        "total": "1.19",
        "ok": "1.19",
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
