/**
 * Created by simone on 03/04/17.
 */

function connectAgencyToSatellite(agencyID, agencyListID) {

    var labelAgencyID = "label_" + agencyID;

    var agencyName = document.getElementById(labelAgencyID).innerHTML;

    var agencyListValue = document.getElementById(agencyListID).value;


    if(document.getElementById(agencyID).checked) {

        addAgencytoInput(agencyListID, agencyListValue, agencyName);

    }else{

        removeAgencyFromInput(agencyListID, agencyListValue, agencyName);
    }

}

function addAgencytoInput(agencyListID, agencyListValue, agencyName) {
    document.getElementById(agencyListID).value = agencyListValue + agencyName + ", ";
}

function removeAgencyFromInput(agencyListID, agencyListValue, agencyName) {
    var newAgencyListValue = agencyListValue.replace(agencyName + ',', '');

    document.getElementById(agencyListID).value = newAgencyListValue;
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

    addAgencytoInput(agencyListID, document.getElementById(agencyListID).value, labelText);

    /*document.getElementById('div_' + IDNumber).appendChild(checkbox);
    document.getElementById('div_' + IDNumber).appendChild(checkboxLabel);
    document.getElementById('div_' + IDNumber).appendChild(buttonDeleteRow);*/

    document.getElementById('agencies_satellite').appendChild(div);

    document.getElementById('label_new_agency').value = '';

}

function removeRow(input, agencyListID, agencyListValue, agencyName) {

    removeAgencyFromInput(agencyListID, agencyListValue, agencyName);

    document.getElementById('agencies_satellite').removeChild( input.parentNode );
}