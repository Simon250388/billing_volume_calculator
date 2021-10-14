
INSERT INTO meter_types(id,version,description)
VALUES (CAST(REPLACE('24c1ce78-d023-4c1a-972a-fc9a5e58f12c','-','') as BINARY),1,'сгв-15');

INSERT INTO meters(id,version,description,meter_type_id)
VALUES
(
CAST(REPLACE('2b531872-8b9e-4331-96e0-b06a221cf3d1','-','') as BINARY)
,1
,'0005151515'
,CAST(REPLACE('24c1ce78-d023-4c1a-972a-fc9a5e58f12c','-','') as BINARY)),
(
CAST(REPLACE('d1850e7a-aeb8-4aa2-abc4-03a5a186a7a5','-','') as BINARY)
,1
,'00022222'
,CAST(REPLACE('24c1ce78-d023-4c1a-972a-fc9a5e58f12c','-','') as BINARY));

INSERT INTO differentiation_types(id,version,Description)
VALUES
(CAST(REPLACE('5ff40f41-aefc-45eb-9308-456b4386682f','-','') as BINARY)
,1
,'Однотарифный'),
(CAST(REPLACE('f2a81484-34dc-4f4b-9d4c-47512b1ee353','-','') as BINARY)
 ,1
 ,'Двух тарифный');


