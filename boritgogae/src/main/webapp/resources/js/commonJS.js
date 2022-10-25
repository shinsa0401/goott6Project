/**
 * 
 */
 function getParameter(param) {
    let returnVal = null;   //리턴값을 저장할 변수
    let url = location.href;
    console.log(url);

    let queryString = url.split("?")[1];
    console.log(queryString);

    let tempArr = queryString.split("&");
    console.log(tempArr);

    for (let i in tempArr) {
        if (tempArr[i].indexOf(param) != -1) {    //tempArr[i]번째에 param이 있다면,,,
            returnVal = tempArr[i].split("=")[1];

            return decodeURIComponent(returnVal);
        }
    }
    return -1; //찾을 매개변수가 없다
}