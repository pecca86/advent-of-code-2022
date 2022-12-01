with open('input.txt', 'r') as file:
    times_increased = 0
    increased_three_slide = 0
    old_val = 99999
    lines = file.readlines()

    # Problem 1
    for line in lines:
        if int(line) > old_val:
            times_increased += 1
        old_val = int(line)

    # Problem 2
    for i in range(len(lines)):
        try:
            if (lines[i] < lines[i+3]):
                increased_three_slide += 1
        except IndexError:
            break
    
print(lines[0])

print(times_increased)
print(increased_three_slide)