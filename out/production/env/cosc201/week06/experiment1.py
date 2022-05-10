
import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("./experiment1.csv")

# df = df.pivot(index="r",columns="k",values="i")
print(df)
plt.figure(figsize=(11,5))
plt.scatter(df["k"],df["i"],c=df["k"])
plt.xticks(range(1,51))
plt.xlabel("number of shuffles (k)")
plt.ylabel("Positionin in which the zero card winds up")
plt.title("Position in which the zero cardwinds up after k shuffles")

plt.show()