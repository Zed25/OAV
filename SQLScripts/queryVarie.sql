INSERT INTO s_c_membership SELECT DISTINCT sources.sourceid, clumps.clumpid
FROM sources CROSS JOIN clumps
            INNER JOIN ellipses ON (clumps.clumpid=ellipses.clump)
WHERE ( (sqrt((sources.galacticlatitude - clumps.galacticlatitude)^2 +
        (sources.galacticlongitude - clumps.galacticlongitude)^2) <
        (ellipses.maxaxis * ellipses.band))
        AND clumps.galacticlongitude>0 AND clumps.galacticlatitude>0
        AND sources.galacticlongitude>0 AND sources.galacticlatitude>0);


--OLD SCmemb quety
INSERT INTO s_c_membership SELECT sources.sourceid, clumps.clumpid
                           FROM sources
                             NATURAL JOIN ellipses INNER JOIN clumps ON (ellipses.clump=clumps.clumpid)
                           WHERE (sqrt((sources.galacticlatitude - clumps.galacticlatitude)^2 +
                                       (sources.galacticlongitude - clumps.galacticlongitude)^2) <
                                  (ellipses.maxaxis * ellipses.band)
                                 AND clumps.galacticlongitude>0 AND clumps.galacticlatitude>0
                                 AND sources.galacticlongitude>0 AND sources.galacticlatitude>0);

--UC8--
SELECT sources.sourceid
FROM sources INNER JOIN collection ON (sources.sourceid=collection.source)
NATURAL JOIN ellipses INNER JOIN clumps ON (ellipses.clump=clumps.clumpid)
WHERE ((ellipses.clump = 180916) AND (ellipses.band = 250) AND (collection.starmap= 'MIPS-GAL') AND
       (sqrt((sources.galacticlatitude - clumps.galacticlatitude)^2 +
             (sources.galacticlongitude - clumps.galacticlongitude)^2) <
        (ellipses.maxaxis * ellipses.band)));


--UC11--
SELECT s_c_membership.source
FROM s_c_membership INNER JOIN collection ON (s_c_membership.source=collection.source)
INNER JOIN fluxes ON (s_c_membership.source=fluxes.source)
WHERE ( (s_c_membership.clump=184166) AND (collection.starmap= 'MIPS-GAL')
        AND (((fluxes.band=4.5)-(fluxes.band=5.8))>0.7)
        AND (((fluxes.band=3.6)-(fluxes.band=4.5))>0.7)
        AND (((fluxes.band=3.6)-(fluxes.band=4.5))>1.4*(((fluxes.band=4.5)-(fluxes.band=5.8)-0.7)+0.15)) );


CREATE VIEW V1 AS
  SELECT f1.source
  FROM fluxes AS f1 INNER JOIN fluxes AS f2 ON f1.source=f2.source
  WHERE ( (f1.band = 4.5) AND (f2.band = 5.8) AND (f1.band-f2.band>0.7));




  SELECT fluxes.source
FROM fluxes INNER JOIN collection ON (fluxes.source=collection.source)
NATURAL JOIN clumps
WHERE ( (clumps.clumpid=184166) AND (collection.starmap= 'MIPS-GAL')
        AND (((fluxes.band=4.5)-(fluxes.band=5.8))>0.7)
        AND (((fluxes.band=3.6)-(fluxes.band=4.5))>0.7)
        AND (((fluxes.band=3.6)-(fluxes.band=4.5))>1.4*(((fluxes.band=4.5)-(fluxes.band=5.8)-0.7)+0.15)) );