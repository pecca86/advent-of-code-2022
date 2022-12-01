with open('calories.txt', 'r') as file:
    totalCals = 0
    caloriesPerElf = []
    lines = file.readlines()
    for line in lines:
        if line.startswith("\n"):
            caloriesPerElf.append(totalCals)
            totalCals = 0
        else:
            totalCals += int(line)

    caloriesPerElf.sort()
    print(caloriesPerElf[-1])
    print(sum(tuple(caloriesPerElf[-3::])))