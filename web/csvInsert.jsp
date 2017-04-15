<%--
  Created by IntelliJ IDEA.
  User: dilettalagom
  Date: 04/04/17
  Time: 14.46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>

<div class="row" style="margin-top: 3%">

    <div class="col s11" style="margin-left: 10%">
        <div class="white">

            <h5 style="color:darkslategray" >Scegli il file da inserire: </h5>


            <div class="" style="margin-left: 26%">

                <form  action="" method="">
                    <p>
                        <input class="with-gap" type="radio" name="filescelto" id="higal" value="higal.csv"/>
                        <label for="higal" style="color: darkslategray">Higal</label>
                    </p>

                    <p>
                        <input class="with-gap" type="radio" name="filescelto" id="higal_additionalinfo" value="higal_additionalinfo.csv"/>
                        <label for="higal_additionalinfo" style="color: darkslategray">Higal_additionalinfo</label>

                    </p>

                    <p>
                        <input class="with-gap" type="radio" name="filescelto" id="mips"  value="mips.csv"/>
                        <label for="mips" style="color: darkslategray">Mips</label>
                    </p>

                    <p>
                        <input class="with-gap" type="radio" name="filescelto" id="r08" value="r08.csv" />
                        <label for="r08" style="color: darkslategray">R08</label>
                    </p>

                    <button class="btn waves-effect waves-light blue" type="submit" style="margin-top:2%" name="validate">Submit
                        <i class="material-icons right">send</i>
                    </button>
                </form>

            </div>



            <form  action="login.jsp" method="post" enctype="multipart/form-data" name="formInsert"> <!--style="display: none;"-->
                <div class="row">
                    <div class="col s12">
                        <div class="col s7">
                            <div class="file-field input-field" >

                                <div class="btn">
                                    <span>File</span>
                                    <input type="file" name="file" accept=".csv"/>
                                </div>

                                <div class="file-path-wrapper">
                                    <input class="file-path validate" type="text" >
                                </div>
                            </div>
                        </div>

                        <button class="btn waves-effect waves-light blue" type="submit" style="margin-top:2%" name="validate">Submit
                            <i class="material-icons right">send</i>
                        </button>

                    </div>
                </div>
            </form>
        </div>

    </div>
</div>


<jsp:include page="footer.jsp"/>

