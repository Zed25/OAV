/**
 * Created by simone on 03/04/17.
 */

function generateAgencyCheckBoxWithLabel(parametricNumber){
    var newCheckBox = document.createElement("input");
    newCheckBox.setAttribute("type", "checkbox");
    newCheckBox.setAttribute("id", "checkbox" + parametricNumber);

    var newLabel = document.createElement("label");
    newLabel.setAttribute("for", "checkbox" + parametricNumber);
    newLabel.setAttribute("id", "agency_label" + parametricNumber);

    document.getElementById("agency_label" + parametricNumber).innerHTML = "<%=agencyList.getAgencyBeansList().get(i).getName()%>";
}