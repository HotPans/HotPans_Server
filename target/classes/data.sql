
INSERT INTO customer (name, mail_address) VALUES ('makopi23', 'makopi23@gmail.com');

INSERT INTO bakery (name, mail_address, address, phone_number1, phone_number2, introduction, image, image_encoding, login_id, login_password)
  VALUES ('パン屋まこぴ', 'bakery_makopi@gmail.com', '東京都品川区', '03-0000-1111', '090-2222-3333',
          '美味しいパンいかがですか', FILE_READ('public/images/bakery01.jpg'), 'data:image/jpeg;base64,', 'user1', 'password1');

INSERT INTO bakery (name, mail_address, address, phone_number1, phone_number2, introduction, image, image_encoding, login_id, login_password)
  VALUES ('パン屋さん02', 'bakery1@gmail.com', '神奈川県横浜市', '03-3333-4444', '090-5555-6666',
          'クリームパンがオススメ！', FILE_READ('public/images/bakery02.jpg'), 'data:image/jpeg;base64,', 'user2', 'password2');

INSERT INTO bread (name, price, introduction, bakery_id, image, image_encoding)
  VALUES ('メロンパン', '120', '外はカリカリ、中はしっとり、美味しいメロンパンです。', '1',
          FILE_READ('public/images/meronpan.jpg'), 'data:image/jpeg;base64,');

INSERT INTO bread (name, price, introduction, bakery_id, image, image_encoding)
  VALUES ('クリームパン', '100', '特製くりーむ！', '2',
          FILE_READ('public/images/cream_bread.jpg'), 'data:image/jpeg;base64,');

/*
INSERT INTO bakerybreadrelation (bakery_id, bread_id)
  VALUES (1, 1);
*/

/*
UPDATE bread SET
image_encoding = 'data:image/jpeg;base64,',
image = FILE_READ('public/images/meronpan.jpg')
WHERE id = 1;


UPDATE bread SET
image_encoding = 'data:image/jpeg;base64,',
image = FILE_READ('public/images/cream_bread.jpg')
WHERE id = 2;
*/