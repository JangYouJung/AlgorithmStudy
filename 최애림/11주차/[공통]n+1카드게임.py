from collections import deque
def solution(coin, cards):
    target = len(cards) + 1
    my_card = cards[:len(cards)//3]
    cards = deque(cards[len(cards)//3:])
    temp_card = []
    stage = 0

    while True:
        if len(cards) >= 2:
            #현재 라운드까지 뽑은 카드들을 기억해 뒀다가 필요할 때마다 동전을 소모해 카드를 가져오는 방식으로 구현해도 됩니다.
            # temp_card에 기억해두기
            temp_card.append(cards.popleft())
            temp_card.append(cards.popleft())
        stage += 1
        continue_flag = 0
        #1
        for i in my_card: #동전 0개 소모
            if target - i in my_card:
                my_card.remove(i)
                my_card.remove(target - i)
                continue_flag = 1
                break
        #2
        if continue_flag == 0 and coin > 0: #동전 1개 소모
            for i in temp_card:
                if target - i in my_card:
                    temp_card.remove(i)
                    my_card.remove(target - i)
                    continue_flag = 1
                    coin -= 1
                    break
        #3
        if continue_flag == 0 and coin > 1:  #동전 2개 소모
            for i in temp_card:
                if target - i in temp_card:
                    temp_card.remove(i)
                    temp_card.remove(target - i)
                    continue_flag = 1
                    coin -= 2
                    break

        if continue_flag:
            continue
        break

    if stage > (target-1)//3+1:
        stage = (target-1)//3+1

    print(stage)
    return stage

def main():
    solution(4	,[3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4])

main()