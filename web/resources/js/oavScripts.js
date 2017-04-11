/**
 * Created by simone on 03/04/17.
 */

function connectCheckBosListToBean(singleElementID, completeListID) {

    var labelID = "label_" + singleElementID;

    var elementName = document.getElementById(labelID).innerHTML;

    var listValue = document.getElementById(completeListID).value;


    if(document.getElementById(singleElementID).checked) {

        addCheckBoxElementToInput(completeListID, listValue, elementName);

    }else{

        removeCheckBoxElementFromInput(completeListID, listValue, elementName);
    }

}

function addCheckBoxElementToInput(completeListID, elementListValue, elementName) {
    document.getElementById(completeListID).value = elementListValue + elementName + ", ";
}

function removeCheckBoxElementFromInput(completeListID, elementListValue, elementName) {
    var newElementListValue = elementListValue.replace(elementName + ',', '');

    document.getElementById(completeListID).value = newElementListValue;
}

function addNewAgency(agencyListID) {

    var div = document.createElement('div');

    var IDNumber = 'checkbox' + document.getElementsByName("agencies_satellite_name").length;

    div.className = 'row';
    div.id = 'div_' + IDNumber;


    var labelText  = document.getElementById('label_new_agency').value;

    var checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.id = IDNumber;
    checkbox.name = 'agencies_satellite_name';
    checkbox.checked = 'checked';
    //checkbox.disabled = true;

    var textnode = document.createTextNode(labelText);

    var checkboxLabel = document.createElement('label');
    checkboxLabel.for = IDNumber;
    checkboxLabel.id = "label_" + IDNumber;
    checkboxLabel.appendChild(textnode);

    var buttonDeleteRow = document.createElement('input');
    buttonDeleteRow.type = 'button';
    buttonDeleteRow.value = '-';
    buttonDeleteRow.onclick = function(){removeRow(this, agencyListID, document.getElementById(agencyListID).value, labelText);};

    div.appendChild(checkbox);
    div.appendChild(checkboxLabel);
    div.appendChild(buttonDeleteRow);

    addCheckBoxElementToInput(agencyListID, document.getElementById(agencyListID).value, labelText);

    /*document.getElementById('div_' + IDNumber).appendChild(checkbox);
    document.getElementById('div_' + IDNumber).appendChild(checkboxLabel);
    document.getElementById('div_' + IDNumber).appendChild(buttonDeleteRow);*/

    document.getElementById('agencies_satellite').appendChild(div);

    document.getElementById('label_new_agency').value = '';

}

function removeRow(input, agencyListID, agencyListValue, agencyName) {

    removeCheckBoxElementFromInput(agencyListID, agencyListValue, agencyName);

    document.getElementById('agencies_satellite').removeChild( input.parentNode );
}