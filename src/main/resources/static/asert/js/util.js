/**
 * Created by lean on 2017/10/9.
 */
function formFormate(formArray) {
    var formData = {};
    formArray.forEach(function (e) {
        formData[e.name] = e.value;
    })
    return formData;
}