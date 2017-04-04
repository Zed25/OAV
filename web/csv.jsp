<%--
  Created by IntelliJ IDEA.
  User: dilettalagom
  Date: 04/04/17
  Time: 14.46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<div class="row">
    <div class="col s6">
        <fieldset class="radiogroup">
            <legend>Scegli il file da modificare</legend>

            <ul class="radio" >
                <li><input type="radio" name="filescelto" id="higal" /> <label for="higal">Higal</label></li>
                <li><input type="radio" name="filescelto" id="higal_additionalinfo" /><label for="higal_additionalinfo">Higal additional Info</label></li>
                <li><input type="radio" name="filescelto" id="mips" /><label for="mips">Mips</label></li>
                <li><input type="radio" name="filescelto" id="r08" /><label for="r08">R08</label></li>
            </ul>
        </fieldset>
    </div>
</div>



<form action="upload" method="post" enctype="multipart/form-data">
    <div class="row">
        <div class="col s12">
            <div class="col s7">
                <div class="file-field input-field">

                    <div class="btn">
                        <span>File</span>
                        <input type="file" name="file" accept=".csv"/>
                    </div>

                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" >
                    </div>
                </div>
            </div>

            <button class="btn waves-effect waves-light" type="submit" style="margin-top:2%" name="validate">Submit
                <i class="material-icons right">send</i>
            </button>

        </div>
    </div>
</form>
