INSERT INTO s_c_membership SELECT sources.sourceid, clumps.clumpid
  FROM sources INNER JOIN collection ON (sources.sourceid=collection.source)
  NATURAL JOIN ellipses INNER JOIN clumps ON (ellipses.clump=clumps.clumpid)
  WHERE (sqrt((sources.galacticlatitude - clumps.galacticlatitude)^2 +
        (sources.galacticlongitude - clumps.galacticlongitude)^2) <
        (ellipses.maxaxis * ellipses.band));

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

WHERE ( (s_c_membership.clump=?) AND (collection.starmap= 'MIPS-GAL'))



FROM sources INNER JOIN collection ON (sources.sourceid=collection.source)
NATURAL JOIN ellipses INNER JOIN clumps ON (ellipses.clump=clumps.clumpid)
WHERE ((ellipses.clump = 180916) AND (ellipses.band = 250) AND (collection.starmap= 'MIPS-GAL') AND
       (sqrt((sources.galacticlatitude - clumps.galacticlatitude)^2 +
             (sources.galacticlongitude - clumps.galacticlongitude)^2) <
        (ellipses.maxaxis * ellipses.band)));