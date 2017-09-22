function findPoints(i, rangeMin, currentTang, rangeMax, arr) {
    var count = 1;
    for (var j = i + 1; j != i;) {
        if (j == arr.length) j = 0;
        var tangValue = arr[j][1]/arr[j][0];
        var x = arr[j][0] * arr[i][0];
        var y = arr[j][1] * arr[i][1];
        if ((0 <= currentTang <= 1 &&  x>= 0 ) || (currentTang <= -1 &&  y>= 0)) {
            if (currentTang <= tangValue && tangValue <= rangeMax) {
                // console.log('find ' + tanArr[j] + ' point:', arr[j]);
                count++;
            }
        } else if ((currentTang > 1 && y >= 0) || (-1 <= currentTang <= 0 && x >= 0)) {
            if (rangeMax >= tangValue || tangValue >= currentTang) {
                // console.log('find ' + tanArr[j] + ' point:', arr[j]);
                count++;
            }
        }

        if (j < arr.length - 1) {
            j++;
        } else {
            j = 0;
        }
    }
    return count;
}
function visiblePoints(points) {
    var arr1 = [];
    var arr2 = [];
    var arr3 = [];
    var arr4 = [];
    for (var i = 0; i < points.length; i++) {
        var x = points[i][0];
        var y = points[i][1];
        if (x >= 0 && y >= 0) {
            arr1.push(points[i]);
        } else if (x <= 0 && y >= 0) {
            arr2.push(points[i]);
        } else if (x <= 0 && y <= 0) {
            arr3.push(points[i]);
        } else {
            arr4.push(points[i]);
        }
    }

    var sortFunction = function (a, b) {
        var v1 = a[1] / a[0];
        var v2 = b[1] / b[0];

        if (a[0] == 0) {
            v1 = Math.tan(Math.PI / 2);
        }
        if (b[0] == 0) {
            v2 = Math.tan(Math.PI / 2);
        }
        return v1 - v2;
    };

    arr1.sort(sortFunction);
    arr2.sort(sortFunction);
    arr3.sort(sortFunction);
    arr4.sort(sortFunction);

    var arr = arr1.concat(arr2).concat(arr3).concat(arr4);
    var result = 0;
    for (var i = 0; i < arr.length; i++) {
        var current = arr[i];
        var currentTang = current[1] / current[0];
        if (current[0] == 0) {
            currentTang = Math.tan(Math.PI / 2);
        }
        var rangeMax = Math.tan(Math.atan(currentTang) + Math.PI / 4);
        var rangeMin = Math.tan(Math.atan(currentTang) - Math.PI / 4);
        var count = findPoints(i, rangeMin, currentTang, rangeMax, arr);
        if (count > result) {
            result = count;
            // console.log('result ' + result);
        }
    }

    return result;
}