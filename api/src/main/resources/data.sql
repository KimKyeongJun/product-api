-- Categories
INSERT INTO category (name) VALUES ('상의'), ('아우터'), ('바지'), ('스니커즈'), ('가방'), ('모자'), ('양말'), ('액세서리');

-- Brands
INSERT INTO brand (name) VALUES ('A'), ('B'), ('C'), ('D'), ('E'), ('F'), ('G'), ('H'), ('I');

-- Products
INSERT INTO product (brand_id, category_id, price, status) VALUES
(1,1,11200, 'ACTIVE'), (1,2,5500, 'ACTIVE'), (1,3,4200, 'ACTIVE'), (1,4,9000, 'ACTIVE'), (1,5,2000, 'ACTIVE'), (1,6,1700, 'ACTIVE'), (1,7,1800, 'ACTIVE'), (1,8,2300, 'ACTIVE')
, (2,1,10500, 'ACTIVE'), (2,2,5900, 'ACTIVE'), (2,3,3800, 'ACTIVE'), (2,4,9100, 'ACTIVE'), (2,5,2100, 'ACTIVE'), (2,6,2000, 'ACTIVE'), (2,7,2000, 'ACTIVE'), (2,8,2200, 'ACTIVE')
, (3,1,10000, 'ACTIVE'), (3,2,6200, 'ACTIVE'), (3,3,3300, 'ACTIVE'), (3,4,9200, 'ACTIVE'), (3,5,2200, 'ACTIVE'), (3,6,1900, 'ACTIVE'), (3,7,2200, 'ACTIVE'), (3,8,2100, 'ACTIVE')
, (4,1,10100, 'ACTIVE'), (4,2,5100, 'ACTIVE'), (4,3,3000, 'ACTIVE'), (4,4,9500, 'ACTIVE'), (4,5,2500, 'ACTIVE'), (4,6,1500, 'ACTIVE'), (4,7,2400, 'ACTIVE'), (4,8,2000, 'ACTIVE')
, (5,1,10700, 'ACTIVE'), (5,2,5000, 'ACTIVE'), (5,3,3800, 'ACTIVE'), (5,4,9900, 'ACTIVE'), (5,5,2300, 'ACTIVE'), (5,6,1800, 'ACTIVE'), (5,7,2100, 'ACTIVE'), (5,8,2100, 'ACTIVE')
, (6,1,11200, 'ACTIVE'), (6,2,7200, 'ACTIVE'), (6,3,4000, 'ACTIVE'), (6,4,9300, 'ACTIVE'), (6,5,2100, 'ACTIVE'), (6,6,1600, 'ACTIVE'), (6,7,2300, 'ACTIVE'), (6,8,1900, 'ACTIVE')
, (7,1,10500, 'ACTIVE'), (7,2,5800, 'ACTIVE'), (7,3,3900, 'ACTIVE'), (7,4,9000, 'ACTIVE'), (7,5,2200, 'ACTIVE'), (7,6,1700, 'ACTIVE'), (7,7,2100, 'ACTIVE'), (7,8,2000, 'ACTIVE')
, (8,1,10800, 'ACTIVE'), (8,2,6300, 'ACTIVE'), (8,3,3100, 'ACTIVE'), (8,4,9700, 'ACTIVE'), (8,5,2100, 'ACTIVE'), (8,6,1600, 'ACTIVE'), (8,7,2000, 'ACTIVE'), (8,8,2000, 'ACTIVE')
, (9,1,11400, 'ACTIVE'), (9,2,6700, 'ACTIVE'), (9,3,3200, 'ACTIVE'), (9,4,9500, 'ACTIVE'), (9,5,2400, 'ACTIVE'), (9,6,1700, 'ACTIVE'), (9,7,1700, 'ACTIVE'), (9,8,2400, 'ACTIVE');