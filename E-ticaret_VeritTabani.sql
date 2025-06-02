-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 02 Haz 2025, 07:59:50
-- Sunucu sürümü: 10.4.32-MariaDB
-- PHP Sürümü: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `e_ticaret`
--
CREATE DATABASE IF NOT EXISTS `e_ticaret` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `e_ticaret`;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `adminler`
--

CREATE TABLE `adminler` (
  `id` int(11) NOT NULL,
  `ad` varchar(50) NOT NULL,
  `soyad` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `sifre` varchar(100) NOT NULL,
  `Tel` bigint(11) NOT NULL,
  `Depart` varchar(20) NOT NULL,
  `Unvan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Tablo döküm verisi `adminler`
--

INSERT INTO `adminler` (`id`, `ad`, `soyad`, `email`, `sifre`, `Tel`, `Depart`, `Unvan`) VALUES
(1, 'Toygar', 'Yıldız', 'toygar@gmail.com', 'Toygar', 0, 'Yönetim', 'CEO'),
(2, 'Ediz', 'Ergin', 'edize@pazarium.com', 'ediz', 5515546814, 'Yönetim', 'CTO'),
(2, 'ş', 'ş', 'ş', 'ş', 5515546811, 'Yönetim', 'CTO');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `discount_codes`
--

CREATE TABLE `discount_codes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `code` varchar(50) NOT NULL,
  `percentage` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Tablo döküm verisi `discount_codes`
--

INSERT INTO `discount_codes` (`id`, `code`, `percentage`) VALUES
(2, 'kupon20', 20),
(3, 'kopun10', 10);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kategoriler`
--

CREATE TABLE `kategoriler` (
  `kategori_id` int(10) UNSIGNED NOT NULL,
  `kategori_adi` varchar(100) NOT NULL,
  `resim_yolu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Tablo döküm verisi `kategoriler`
--

INSERT INTO `kategoriler` (`kategori_id`, `kategori_adi`, `resim_yolu`) VALUES
(4, 'kıyafet', 'kıyafet.png'),
(5, 'telefon', 'telefon.png'),
(6, 'laptop', 'laptop.png'),
(7, 'meyve', 'meyve.png'),
(8, 'sebze', 'sebze.png'),
(10, 'kıyafetler', 'kıyafetler.png'),
(11, 'tablet', 'tablet.png');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kullanici`
--

CREATE TABLE `kullanici` (
  `id` int(11) NOT NULL,
  `ad` varchar(50) NOT NULL,
  `soyad` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `sifre` varchar(255) NOT NULL,
  `adres` varchar(400) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Tablo döküm verisi `kullanici`
--

INSERT INTO `kullanici` (`id`, `ad`, `soyad`, `email`, `sifre`, `adres`) VALUES
(1, 'ş', 'ş', 'ş', 'ş', 'asdasdasdas'),
(2, 'ali', 'veli', 'ali@gmail.com', '123', 'demokrasi mahallesi atatürk caddesi seyit onbaşı sokak\nno:10 daire:2 Sancaktepe/İstanbul'),
(3, 'şkadfjş', 'asdgf', 'afdsgas@gmail.com', 'Ali1235456789', ''),
(4, 'furkan', 'yasar', 'furkan112@gmail.com', 'Asd123_', NULL);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `mesajlar`
--

CREATE TABLE `mesajlar` (
  `id` int(11) NOT NULL,
  `gonderen_id` int(11) NOT NULL,
  `gonderen_tipi` enum('admin','satici') NOT NULL,
  `alici_id` int(11) NOT NULL,
  `alici_tipi` enum('admin','satici') NOT NULL,
  `mesaj` text NOT NULL,
  `tarih` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `saticilar`
--

CREATE TABLE `saticilar` (
  `id` int(11) NOT NULL,
  `satici_kod` varchar(50) NOT NULL,
  `ad` varchar(255) NOT NULL,
  `soyad` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sifre` varchar(255) NOT NULL,
  `Bloklu` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Tablo döküm verisi `saticilar`
--

INSERT INTO `saticilar` (`id`, `satici_kod`, `ad`, `soyad`, `email`, `sifre`, `Bloklu`) VALUES
(1, 'ş', 'ş', 'ş', 'ş', 'ş', 0),
(2, '2', 'asrgarhg', 'asrgagr', 'sagag@gmail.com', 'Asgagf1523548979', 1),
(3, '3', 'ali', 'veli', 'ali@gmail.com', 'Ali123', 0),
(4, 'sdsdsdsdsd', 'Mehmet', 'Bilir', 'Mehmet@gmail.com', 'M123456789', 0),
(5, 'fgfgfgfgfg', 'Selin', 'Güneş', 'Selin@gmail.com', 'Selin123.', 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `urunler`
--

CREATE TABLE `urunler` (
  `urun_id` int(10) UNSIGNED NOT NULL,
  `kategori_id` int(10) UNSIGNED NOT NULL,
  `ad` varchar(100) NOT NULL,
  `aciklama` text DEFAULT NULL,
  `fiyat` decimal(10,2) NOT NULL,
  `stok` int(11) NOT NULL,
  `resim_yolu` varchar(255) DEFAULT NULL,
  `satici_id` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Tablo döküm verisi `urunler`
--

INSERT INTO `urunler` (`urun_id`, `kategori_id`, `ad`, `aciklama`, `fiyat`, `stok`, `resim_yolu`, `satici_id`) VALUES
(9, 6, 'macbook air', 'macbook air 2 gb ram 8 gb depolama alanı', 100000.00, 100, 'macbook air 2 gb ram 8 gb depolama alanı.png', 1),
(10, 6, 'acer laptop', 'acer laptop 16 gb ram 256gb ssd', 60000.00, 100, 'acer laptop 16 gb ram 256gb ssd.png', 1),
(11, 6, 'dell laptop', 'dell laptop 8 gb 500 gb ssd', 5000.00, 12, 'dell laptop 8 gb 500 gb ssd.png', 1),
(12, 10, 'kapşonlu sweath', 'kahverengi kapşonlu L beden Sweatshirt', 2000.00, 25, 'kahverengi kapşonlu L beden Sweatshirt.png', 1),
(13, 7, 'nar', 'Sulu Sulu nar bir tane alırsın bin olur', 100.00, 50, 'Sulu Sulu nar bir tane alırsın bin olur.png', 1),
(14, 10, 'yakalı tshirt', 'gri yakalı tshirt M beden', 200.00, 12, 'gri yakalı tshirt M beden.png', 1),
(15, 8, 'Mısır', 'Süt Mısır', 25.00, 25, 'Süt Mısır.png', 1),
(16, 10, 'Bordo Hodie', 'Bordo Renk Hodie S beden', 600.00, 50, 'Bordo Renk Hodie S beden.png', 1),
(17, 8, 'Acı Biber', 'Acı Biber Yakar', 100.00, 60, 'Acı Biber Yakar.png', 1),
(18, 10, 'Pantolon Mavi', 'Mavi kot pantolon jean 38 Beden', 900.00, 25, 'Mavi kot pantolon jean 38 Beden.png', 1),
(19, 10, 'Siyah sort', 'yazlık şort L beden', 500.00, 5, 'yazlık şort L beden.png', 1),
(20, 10, 'Tshirt', 'papatyalı tshirt', 500.00, 12, 'papatyalı tshirt.png', 1),
(21, 10, 'Kapşonlu Hodie', 'Beyaz Hoide Kapşonlu S Beden', 250.00, 12, 'Beyaz Hoide Kapşonlu S Beden.png', 1),
(22, 10, 'Gömlek Kısa', 'Kısa kollu Gömlek', 500.00, 12, 'Kısa kollu Gömlek.png', 1),
(23, 7, 'ejder meyvesi', 'ejder meyvesi en iyisi 1 kilo', 60000.00, 500, 'ejder meyvesi en iyisi 1 kilo.png', 1),
(24, 10, 'Boğazlı Kazak', 'Boğazlı Açık Kahve Kazak S BEden', 690.00, 50, 'Boğazlı Açık Kahve Kazak S BEden.png', 1),
(25, 10, 'Düğmeli Ceket', 'Düğmeli Ceket M beden', 6000.00, 5, 'Düğmeli Ceket M beden.png', 1),
(26, 11, 'tablet apple', 'Mini Apple Tablet 2 gb ram Çöpten Farksız', 4520.00, 25, 'Mini Apple Tablet 2 gb ram Çöpten Farksız.png', 1),
(27, 10, 'BEyaz kazak', 'Şekilli Örgü Kazak S beden', 422.00, 12, 'Şekilli Örgü Kazak S beden.png', 1),
(28, 10, 'Açık Pantolon Jean', 'Bol Paça Mavi Pantolon 400 beden', 1111.00, 12, 'Bol Paça Mavi Pantolon 400 beden.png', 1),
(29, 8, 'Bamya', 'Bamyalar çok sadece', 132.00, 12, 'Bamyalar çok sadece.png', 1),
(30, 11, 'Tablet appleeee', 'Apple air 25gb ssd', 12.00, 12, 'Apple air 25gb ssd.png', 1),
(31, 10, 'Oversize Tshirt', 'Beş kısa kollu Tshirt s BEden oversize', 12314.00, 212, 'Beş kısa kollu Tshirt s BEden oversize.png', 1),
(32, 10, 'Gömlek Dantelli', 'Beyaz anneanne Danteli Gömlek', 4325.00, 12, 'Beyaz anneanne Danteli Gömlek.png', 1),
(33, 7, 'mavi meyve', 'mavili meyve çok iyidir', 3543.00, 12, 'mavili meyve çok iyidir.png', 1),
(34, 10, 'Gri Tshirt', 'Gri Tshirt S beden', 4234.00, 12, 'Gri Tshirt S beden.png', 1),
(35, 5, 'İphone 11', 'İphone 11 hala alınıyor', 23413.00, 453, 'İphone 11 hala alınıyor.png', 1),
(36, 10, 'Tshirt', 'Bej Tshirt DEĞişik bir tisrt', 334532.00, 12, 'Bej Tshirt DEĞişik bir tisrt.png', 1),
(37, 5, 'iphone 13', 'yeşil kkasa iphone 12 gb 1 tb', 2134323.00, 1254, 'yeşil kkasa iphone 12 gb 1 tb.png', 1),
(38, 8, 'brokoli', 'broklü 1 kilo', 1234.00, 43, 'broklü 1 kilo.png', 1),
(39, 5, 'iphone 13', 'siyah iphone 13', 3342.00, 12, 'siyah iphone 13.png', 1),
(40, 10, 'uzun ceket', 'siyah ceket uzun boylu m beden', 12342.00, 123, 'siyah ceket uzun boylu m beden.png', 1),
(41, 10, 'kahve tulum', 'kalın tulum çok ii', 123.00, 43, 'kalın tulum çok ii.png', 1),
(42, 10, 'mafya ceketi', 'deri ceket m beden mafya gibi', 432.00, 123, 'deri ceket m beden mafya gibi.png', 1),
(43, 11, 'tablet', 'tablet android', 123123.00, 12343, 'tablet android.png', 1),
(44, 10, 'blazer ceket', 'düğmeli blazer ceket  s beden', 123.00, 32, 'düğmeli blazer ceket  s beden.png', 1),
(45, 11, 'tablet mini', 'apple tablet mini 2 gb', 123.00, 432, 'apple tablet mini 2 gb.png', 1),
(46, 10, 'mavi gömlek', 'mavi gömlek s benden', 24564.00, 23, 'mavi gömlek s benden.png', 1),
(47, 7, 'Avakado', 'avakoda 123 kilo diyet için', 323.00, 12, 'avakoda 123 kilo diyet için.png', 1),
(48, 6, 'asus 12', 'asus  çok kaliteli laptop', 999.00, 554, 'asus  çok kaliteli laptop.png', 1),
(49, 5, 'google pixel', 'google pixel 9 gb', 123.00, 43, 'google pixel 9 gb.png', 1),
(50, 11, 'tabletmiş', 'tablettttt', 432.00, 23, 'tablettttt.png', 1),
(51, 10, 'bej yelek', 'bej yelek s beden', 12344.00, 43, 'bej yelek s beden.png', 1),
(52, 10, 'elbsie siyah', 'siyah düğmeli elbise', 32423.00, 43, 'siyah düğmeli elbise.png', 1),
(53, 10, 'örgülü kazak', 'haki yeşil örghülü kazak', 32423.00, 43, 'haki yeşil örghülü kazak.png', 1),
(54, 11, 'samsung tablet', 'tablşet tab 9 s ss', 4643.00, 23, 'tablşet tab 9 s ss.png', 1),
(55, 7, 'ananas', 'anansaz 19 kilo', 342.00, 45, 'anansaz 19 kilo.png', 1),
(56, 11, '9. nesil iphone', 'tablet iphone 9. nesil', 2314.00, 32, 'tablet iphone 9. nesil.png', 1),
(57, 10, 'gömlek artı tisrt', 'Göömlek artı tişrt', 234.00, 45, 'Göömlek artı tişrt.png', 1),
(58, 10, 'polo kaya kıyafet', 'ppolo yuaka lacivert s beden', 3423423.00, 123, 'ppolo yuaka lacivert s beden.png', 1),
(59, 10, 'üst alt pijama takımı', 'üst alt pijama takımı kısa', 43242.00, 453, 'üst alt pijama takımı kısa.png', 1),
(60, 10, 'uzun etek', 'uzun siyah etek', 231.00, 43, 'uzun siyah etek.png', 1),
(61, 7, 'muz', 'sap sarı muzlar 100 kilo', 52.00, 43, 'sap sarı muzlar 100 kilo.png', 1),
(62, 10, 'kısa ceket', 'kısa ceket çok ii', 342.00, 43, 'kısa ceket çok ii.png', 1),
(63, 7, 'çilek', 'çilek sulu bal gibi', 321.00, 32, 'çilek sulu bal gibi.png', 1),
(64, 6, 'samsung laptop', 'samsung laptoplar sınırlı stok', 323.00, 42, 'samsung laptoplar sınırlı stok.png', 1),
(65, 10, 'crop', 'crop kahve ve beyaz desenli', 324.00, 43, 'crop kahve ve beyaz desenli.png', 1),
(66, 7, 'böğürtlen', 'böğürtlen 2 kilo', 32.00, 3, 'böğürtlen 2 kilo.png', 1),
(67, 6, 'lenovo 4', 'lenova laptop 55', 23456.00, 2, 'lenova laptop 55.png', 1),
(68, 6, 'gaming laptop', 'ağırl lpatop 6090 var içinde', 556555.00, 43, 'ağırl lpatop 6090 var içinde.png', 1),
(69, 10, 'haki hırka', 'haki yeşil hırka düğmeli', 1234.00, 32, 'haki yeşil hırka düğmeli.png', 1),
(70, 6, 'hp laptop', 'hp laptop 5 gb ram ofis için iyi', 4555.00, 23, 'hp laptop 5 gb ram ofis için iyi.png', 1),
(71, 7, 'kiraz', 'kıpkırmızı kiraz', 342432.00, 34, 'kıpkırmızı kiraz.png', 1),
(72, 6, 'lenova s24', 'lenova d14 256 gb laprop', 342.00, 23, 'lenova d14 256 gb laprop.png', 1),
(73, 10, 'hoide 4 lü', '4 lü hodie tekli satılmaz', 1234.00, 32, '4 lü hodie tekli satılmaz.png', 1),
(74, 6, 'thinkpad', 'lenova thikpad harika bir laptop', 2133.00, 23, 'lenova thikpad harika bir laptop.png', 1),
(75, 10, 'sweathirt', 'beyaz sweatshir m beden', 123.00, 222, 'beyaz sweatshir m beden.png', 1),
(76, 10, 'açık mavi hoide', 'açık mavi swets m beden', 5455.00, 23, 'açık mavi swets m beden.png', 1),
(77, 10, 'çinli takım', 'çinli takım işte', 324.00, 4, 'çinli takım işte.png', 1),
(78, 10, 'koyu gömlek', 'koyu kahve gömlek', 43443.00, 3, 'koyu kahve gömlek.png', 1),
(79, 5, 'samsung s23', 's23 telefon 256 gb', 344334.00, 32, 's23 telefon 256 gb.png', 1),
(80, 8, 'patlıcan', 'patlıcan 2 kilo', 44444.00, 234, 'patlıcan 2 kilo.png', 1),
(81, 10, 'açık gömlek', 'a.ık mavi crop gömlek', 434.00, 23, 'a.ık mavi crop gömlek.png', 1),
(82, 10, 'macbook m1', 'macbook m1 air 8 gb 128 ssd', 4444.00, 23, 'macbook m1 air 8 gb 128 ssd.png', 1),
(83, 7, 'karpuz', 'karpuz sulu sulu kilosu', 23.00, 232, 'karpuz sulu sulu kilosu.png', 1),
(84, 10, 'crop 3lü', '3lü crop set', 43.00, 43, '3lü crop set.png', 1),
(85, 8, 'havuç', 'havuçalar tavşanlar sever', 3443.00, 34, 'havuçalar tavşanlar sever.png', 1),
(86, 10, 'renkli ceket', 'ceket renkli ceket 23', 2333.00, 22, 'ceket renkli ceket 23.png', 1),
(87, 7, 'hindistancevizi', 'hindistan cevisi 2 kilo  arizonadan geldi', 33.00, 23, 'hindistan cevisi 2 kilo  arizonadan geldi.png', 1),
(88, 10, 'gir takım', 'gri takım s beden', 444.00, 34, 'gri takım s beden.png', 1);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `discount_codes`
--
ALTER TABLE `discount_codes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Tablo için indeksler `kategoriler`
--
ALTER TABLE `kategoriler`
  ADD PRIMARY KEY (`kategori_id`),
  ADD UNIQUE KEY `kategori_adi` (`kategori_adi`);

--
-- Tablo için indeksler `kullanici`
--
ALTER TABLE `kullanici`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Tablo için indeksler `mesajlar`
--
ALTER TABLE `mesajlar`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `saticilar`
--
ALTER TABLE `saticilar`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `satici_kod` (`satici_kod`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Tablo için indeksler `urunler`
--
ALTER TABLE `urunler`
  ADD PRIMARY KEY (`urun_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `discount_codes`
--
ALTER TABLE `discount_codes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `kategoriler`
--
ALTER TABLE `kategoriler`
  MODIFY `kategori_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Tablo için AUTO_INCREMENT değeri `kullanici`
--
ALTER TABLE `kullanici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `mesajlar`
--
ALTER TABLE `mesajlar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `saticilar`
--
ALTER TABLE `saticilar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `urunler`
--
ALTER TABLE `urunler`
  MODIFY `urun_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
