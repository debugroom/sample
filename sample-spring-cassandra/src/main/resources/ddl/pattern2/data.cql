USE sample;

INSERT INTO users JSON '{"user_id": 0, "user_name": "debugroom", "login_id": "org.debugroom", "address": {"zip_cd": "351-0033", "address" : "Saitama Asaka Hamasaki"}, "previous": [ {"zip_cd": "101-0032", "address": "Tokyo Chiyoda Iwamoto" }, {"zip_cd": "851-0051", "address": "Nagasaki Nagasaki Sakurababa"} ] }' IF NOT EXISTS ;