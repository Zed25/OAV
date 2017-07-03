--NEW SCmemb query
INSERT INTO s_c_membership SELECT DISTINCT sources.sourceid, clumps.clumpid
FROM sources CROSS JOIN clumps
            INNER JOIN ellipses ON (clumps.clumpid=ellipses.clump)
WHERE ( (sqrt((sources.galacticlatitude - clumps.galacticlatitude)^2 +
        (sources.galacticlongitude - clumps.galacticlongitude)^2) <
        (ellipses.maxaxis * ellipses.band))
        AND clumps.galacticlongitude>0 AND clumps.galacticlatitude>0
        AND sources.galacticlongitude>0 AND sources.galacticlatitude>0);
--OLD SCmemb query
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


--UC11--
CREATE VIEW V1 AS
SELECT f1.source AS V1source, (f1.value-f2.value) AS difvalue4558
FROM fluxes AS f1 INNER JOIN fluxes AS f2 ON f1.source=f2.source
WHERE ( ((f1.band=4.5) OR (f2.band=5.8)) AND ((f1.value-f2.value)>0.7) );

CREATE VIEW V2 AS
  SELECT f1.source AS V2source, (f1.value-f2.value) AS difvalue3645
  FROM fluxes AS f1 INNER JOIN fluxes AS f2 ON f1.source=f2.source
  WHERE ( ((f1.band=3.6) OR (f2.band=4.5)) AND ((f1.value-f2.value)>0.7) );

SELECT DISTINCT V1.V1source
FROM V1 INNER JOIN V2 ON (V1.V1source=V2.V2source)
  INNER JOIN s_c_membership ON (V1.V1source=s_c_membership.source)
WHERE ( (V2.difvalue3645 > 1.4* (V1.difvalue4558 -0.7) +0.15) AND (s_c_membership.clump=181599) );

