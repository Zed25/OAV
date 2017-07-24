--NEW SCmemb query

SELECT DISTINCT sources.sourceid, clumps.clumpid, sources.galacticlatitude,sources.galacticlongitude,
  clumps.galacticlatitude, clumps.galacticlongitude, ellipses.maxaxis
FROM sources CROSS JOIN
     clumps INNER JOIN ellipses ON (clumps.clumpid=ellipses.clump)
WHERE ( ( sqrt((sources.galacticlatitude - clumps.galacticlatitude)^2 +
        (sources.galacticlongitude - clumps.galacticlongitude)^2) <
        ellipses.maxaxis*3600)
        AND clumps.galacticlongitude!=0 AND clumps.galacticlatitude!=0
        AND sources.galacticlongitude!=0 AND sources.galacticlatitude!=0);


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

--UCSourcesquarecircle--

--circle--

SELECT sources.sourceid, count(*)<=n
  FROM sources
  WHERE ( sqrt( (sources.galacticlatitude - latinput)^2 +
                (sources.galacticlongitude - longinput)^2 ) <= raggio );
--square--

SELECT sources.sourceid, count(*)<=n
FROM sources
WHERE ( sqrt( (sources.galacticlatitude - latinput)^2 +
              (sources.galacticlongitude - longinput)^2 ) <= raggio );



--dencityClump--

SELECT *
FROM clumps JOIN s_c_membership ON clumps.clumpid = s_c_membership.clump
WHERE (clumps.surfacedensity > 0.1 AND clumps.surfacedensity < 1.0
AND clumps.clumpid = 181578);

SELECT *
FROM s_c_membership
WHERE s_c_membership.clump=181578;






