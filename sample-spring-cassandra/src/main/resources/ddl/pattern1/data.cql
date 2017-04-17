USE sample;

INSERT INTO users JSON '{"user_id": 0, "user_name": "debugroom", "login_id": "org.debugroom", "address": {"zip_cd": "101-0032", "address" : "Tokyo Chiyoda Iwamoto"} }' IF NOT EXISTS ;
INSERT INTO users JSON '{"user_id": 1, "user_name": "(ΦωΦ)", "login_id": "(・∀・)", "address": {"zip_cd": "351-0033", "address" : "Saitama asaka hamasaki"} }' IF NOT EXISTS ;