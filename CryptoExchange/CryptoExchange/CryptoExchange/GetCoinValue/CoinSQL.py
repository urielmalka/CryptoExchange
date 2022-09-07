from mysql.connector import connect


class CoinSQL:

    apis: list
    coinsname: list

    def __init__(self):
        """"Set your DB deatali for see the project"""

        """Connection to the DB coins"""
        self.coinsDB = connect(
            host="127.0.0.1",
            user="root",
            password="my password",
            database="coins"
        )
        self.mycursor = self.coinsDB.cursor()

        """Read APIs from SQL db"""
        self.mycursor.execute("SELECT api FROM api")
        result = self.mycursor.fetchall()
        self.apis = [x[0] for x in result]

        """Read coins name from SQL db"""
        self.mycursor.execute("SELECT CNAME FROM coins")
        result = self.mycursor.fetchall()
        self.coinsname = [x[0] for x in result]

    def update_value(self,coin,value):

        sql = f"UPDATE coins_value SET dollar_value = {value} WHERE CID = {self.coinsname.index(coin)}"
        self.mycursor.execute(sql)
        self.coinsDB.commit()
        print(f"Update: {coin} : {value}")