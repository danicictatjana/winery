INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

INSERT INTO tip_vina (id, ime) VALUES (1, 'Suvo belo vino');
INSERT INTO tip_vina (id, ime) VALUES (2, 'Poluslatko belo vino');
INSERT INTO tip_vina (id, ime) VALUES (3, 'Suvo crveno vino');

INSERT INTO vinarija (id, ime, godina_osnivanja) VALUES (1, 'Vinarija Zvonko Bogdan', 1988);
INSERT INTO vinarija (id, ime, godina_osnivanja) VALUES (2, 'Vinarija Draskovic', 2000);
INSERT INTO vinarija (id, ime, godina_osnivanja) VALUES (3, 'Vinarija Brakocevic', 2001);

INSERT INTO vino (id, ime, opis, godina_proizvodnje, cena_flase, broj_dostupnih_flasa, tip_vina_id, vinarija_id) VALUES (1, '8 tamburasa', 'Lagano belo vino sa letnjim vocem', 2015, 1200, 25, 1, 1);
INSERT INTO vino (id, ime, opis, godina_proizvodnje, cena_flase, broj_dostupnih_flasa, tip_vina_id, vinarija_id) VALUES (2, 'Muskat ottonel', 'Raskosna aromatika ove sorte, sveze i pitko vino', 2016, 700, 50, 2, 2);
INSERT INTO vino (id, ime, opis, godina_proizvodnje, cena_flase, broj_dostupnih_flasa, tip_vina_id, vinarija_id) VALUES (3, 'Zivot tece', 'Zrele crne tresnje, sljive i kupine sa tonovima vanile', 2010, 900, 100, 3, 3);
