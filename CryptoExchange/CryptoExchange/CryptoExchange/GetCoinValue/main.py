import asyncio
import time

import requests

from CoinSQL import CoinSQL


async def get_coin(coin,keys,sql):
    counter_key = 0
    while True:
        if counter_key >= len(keys):
            break
        url = 'https://rest.coinapi.io/v1/exchangerate/'+coin+'/USD'
        headers = {'X-CoinAPI-Key': keys[counter_key]}
        response = requests.get(url, headers=headers)
        value = response.json()
        try:
            val = float(value['rate'])
            sql.update_value(coin=coin,value=val)
        except Exception as e:
            counter_key += 1
            print('Next key',counter_key+1)
        await asyncio.sleep(4)

async def get_all_coin():
    loop_task = asyncio.get_event_loop()
    tasks = []
    coinSQL = CoinSQL()
    for coin in coinSQL.coinsname[1:]:
        tasks.append(loop_task.create_task(get_coin(coin,coinSQL.apis,sql=coinSQL)))
        await asyncio.sleep(0.2)
    await asyncio.wait(tasks)

if __name__ == '__main__':

    asyncio.set_event_loop(asyncio.SelectorEventLoop())
    loop = asyncio.get_event_loop()
    loop.run_until_complete(get_all_coin())
