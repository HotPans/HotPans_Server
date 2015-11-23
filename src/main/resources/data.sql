


INSERT INTO customer (name, mail_address, login_id, encoded_login_password)
  VALUES ('customer1', 'customer1@gmail.com', 'customer1', '+U2qzAwKp3jCl+seG6FZMr7/EfFXgj2C7zde20AAZQU=');

INSERT INTO customer (name, mail_address, login_id, encoded_login_password)
  VALUES ('customer2', 'customer2@gmail.com', 'customer2', 'h5LLcDocthbuJyggeGw3ZptBgaRdd+bU+gJa0MjqoCc=');

INSERT INTO customer (name, mail_address) VALUES ('makopi23', 'makopi23@gmail.com');

INSERT INTO bakery (name, mail_address, address, phone_number1, phone_number2, introduction, image, image_encoding, login_id,
  -- login_password)
  encoded_login_password)
  VALUES ('パン屋まこぴ', 'bakery_makopi@gmail.com', '東京都品川区', '03-0000-1111', '090-2222-3333',
          '美味しいパンいかがですか', FILE_READ('public/images/bakery01.jpg'), 'data:image/jpeg;base64,', 'bakery1',
          -- 'password1');
          '+U2qzAwKp3jCl+seG6FZMr7/EfFXgj2C7zde20AAZQU=');

INSERT INTO bakery (name, mail_address, address, phone_number1, phone_number2, introduction, image, image_encoding, login_id,
  -- login_password)
  encoded_login_password)
  VALUES ('パン屋さん02', 'bakery1@gmail.com', '神奈川県横浜市', '03-3333-4444', '090-5555-6666',
          'クリームパンがオススメ！', FILE_READ('public/images/bakery02.jpg'), 'data:image/jpeg;base64,', 'bakery2',
          -- 'password2');
          'h5LLcDocthbuJyggeGw3ZptBgaRdd+bU+gJa0MjqoCc=');

INSERT INTO bread (name, price, introduction, bakery_id, image, image_encoding)
  VALUES ('メロンパン', '120', '外はカリカリ、中はしっとり、美味しいメロンパンです。', '1',
          FILE_READ('public/images/meronpan.jpg'), 'data:image/jpeg;base64,');

INSERT INTO bread (name, price, introduction, bakery_id, image, image_encoding)
  VALUES ('クリームパン', '100', '特製くりーむ！', '2',
          FILE_READ('public/images/cream_bread.jpg'), 'data:image/jpeg;base64,');

INSERT INTO administrator (name, mail_address, login_id, encoded_login_password)
  VALUES ('makopi23', 'kipersonalpc@gmail.com', 'kipersonalpc', 'coc3+Y6up72wBP8/nbj6ERzz8C+dHDJdWzLYW8j418U=');

INSERT INTO administrator (name, mail_address, login_id, encoded_login_password)
  VALUES ('kamiya256', 'kamiya256@gmail.com', 'kamiya256', 'pzPdB/j0yXkrjRgSAq07NM2RSjmFN3cohs+YJV6FAY4=');

INSERT INTO administrator (name, mail_address, login_id, encoded_login_password)
  VALUES ('kimura.kanataku', 'kimura.kanataku@gmail.com', 'kimura.kanataku', 'IkxuC4xIa0732cJzx6bqSH4JdgVVVGw/H49oUGpVQtI=');

INSERT INTO administrator (name, mail_address, login_id, encoded_login_password)
  VALUES ('seven.keyaki', 'seven.keyaki@gmail.com', 'seven.keyaki', 'fio3et+M8pj1SInxyA9nzHsoq3Pqi7noUAY5sDJAzk8=');


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