/**
 * Created by simone on 03/04/17.
 */

function connectAgencyToSatellite(agencyID, agencyListID) {

    var labelAgencyID = "label_" + agencyID;

    var agencyName = document.getElementById(labelAgencyID).innerHTML;

    var agencyListValue = document.getElementById(agencyListID).value;


    if(document.getElementById(agencyID).checked) {

        document.getElementById(agencyListID).value = agencyListValue + agencyName + ", ";
    }else{

        var newAgencyListValue = agencyListValue.replace(agencyName + ',', '');

        document.getElementById(agencyListID).value = newAgencyListValue;
    }

}

function addNewAgency() {

    var div = document.createElement('div');

    var IDNumber = 'checkbox' + document.getElementsByName("agencies_satellite_name").length;

    div.className = 'row';
    div.id = 'div_' + IDNumber;


    var labelText  = document.getElementById('label_new_agency').value;

    var checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.id = IDNumber;
    checkbox.name = 'agencies_satellite_name';
    checkbox.onchange = connectAgencyToSatellite(this.id, 'agencies_to_satellite');

    var textnode = document.createTextNode(labelText);

    var checkboxLabel = document.createElement('label');
    checkboxLabel.for = IDNumber;
    checkboxLabel.id = "label_" + IDNumber;
    checkboxLabel.appendChild(textnode);

    var buttonDeleteRow = document.createElement('input');
    buttonDeleteRow.type = 'button';
    buttonDeleteRow.value = '-';
    buttonDeleteRow.onclick = removeRow(this);

    div.appendChild(checkbox);
    div.appendChild(checkboxLabel);
    div.appendChild(buttonDeleteRow);

    /*document.getElementById('div_' + IDNumber).appendChild(checkbox);
    document.getElementById('div_' + IDNumber).appendChild(checkboxLabel);
    document.getElementById('div_' + IDNumber).appendChild(buttonDeleteRow);*/

    document.getElementById('agencies_satellite').appendChild(div);
}

function removeRow(input) {
    document.getElementById('agencies_satellite').removeChild( input.parentNode );
}