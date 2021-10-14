INSERT INTO PROVIDERS(id,version,Description)
VALUES (CAST(REPLACE('2adadecc-fd7e-4f0c-a953-4d9af248ab0b','-','') as BINARY),1,'Мосэнерго'),
 (CAST(REPLACE('2e97037d-58d5-4b44-8fd1-d8b91c02896a','-','') as BINARY),1,'Ленэнерго');

INSERT INTO SERVICES(id,version,Description)
VALUES (CAST(REPLACE('74c29c03-b528-4f24-95e0-c68cd1e42574','-','') as BINARY),1,'Электроэнергия');

INSERT INTO DIRECTION_OF_USES(id,version,Description)
VALUES (CAST(REPLACE('718556e7-6b3d-448e-a307-2a0775b251f8','-','') as BINARY),1,'Основное направление');
