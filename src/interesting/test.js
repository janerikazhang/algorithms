function findCCW(i, cw, c, ccw, arr) {
    var countj = 1, countk = 1;
    var finishj = false, finishk = false;
    // console.log('range from ' + c + ' range to ' + ccw);
    for (var j = i + 1, k = i -1; j != arr.length && k >= 0;) {
        var vj = arr[j][1] / arr[j][0];
        var xj = arr[j][0] * arr[i][0];
        var yj = arr[j][1] * arr[i][1];

        var vk = arr[k][1] / arr[k][0];
        var xk = arr[k][0] * arr[k][0];
        var yk = arr[k][1] * arr[k][1];
        if (! finishj) {
            if ((0 <= c <= 1 && xj >= 0 ) || (c <= -1 && yj >= 0)) {
                if (c <= vj && vj <= ccw) {
                    // console.log('find point:', arr[j]);
                    countj++;
                } else {
                    finishj = true;
                }
            } else if ((c > 1 && yj >= 0) || (-1 <= c <= 0 && xj >= 0)) {
                if (ccw >= vj || vj >= c) {
                    // console.log('find point:', arr[j]);
                    countj++;
                } else {
                    finishj = true;
                }
            }
        }

        if (! finishk) {
            if ((0 <= cw <= 1 && xk >= 0 ) || (cw <= -1 && yk >= 0)) {
                if (cw <= vk && vk <= c) {
                    // console.log('find point:', arr[j]);
                    countk++;
                } else {
                    finishk = true;
                }
            } else if ((cw > 1 && yk >= 0) || (-1 <= cw <= 0 && xk >= 0)) {
                if (c >= vk || vk >= cw) {
                    // console.log('find point:', arr[j]);
                    countk++;
                } else {
                    finishk = true;
                }
            }
        }


        if (j < arr.length - 1 && j != i ) {
            j++;
        } else if (j != i) {
            j = 0;
        } else if (j == i) {
            finishj = true;
        }

        if (k > 0 && k != i) {
            k--;
        } else if (k != i){
            k = arr.length - 1;
        } else if (k == i){
            finishk = true;
        }

        if (j == i && k == i) break;
    }

    if (countj > countk) {
        return countj
    } else {
        return countk;
    }
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

    var sortFun = function (a, b) {
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

    arr1.sort(sortFun);
    arr2.sort(sortFun);
    arr3.sort(sortFun);
    arr4.sort(sortFun);

    var arr = arr1.concat(arr2).concat(arr3).concat(arr4);
    // console.log('arr ', arr);
    var result = 0;
    for (var i = 0; i < arr.length; i++) {
        var current = arr[i];
        var currentTang = getPreciseValue(current[1] / current[0]);
        if (current[0] == 0) {
            currentTang = Math.tan(Math.PI / 2);
        }
        var rccw = getPreciseValue(Math.tan(Math.atan(currentTang) + Math.PI / 4));
        var rcw = getPreciseValue(Math.tan(Math.atan(currentTang) - Math.PI / 4));
        var count = findCCW(i, rcw, currentTang, rccw, arr);
        if (count > result) {
            result = count;
            // console.log('result ' + result);
        }
    }
    return result;
}

function getPreciseValue(rawX) {
    roundedX = Math.round(100000000 * rawX) / 100000000;
    resultStr = roundedX.toFixed(8);
    return resultStr;
}