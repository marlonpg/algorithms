type PairEntry = {
  sum: number;
  left: number;
  gen: number;
};

class MinHeap {
  private data: PairEntry[] = [];

  public push(value: PairEntry): void {
    this.data.push(value);
    this.siftUp(this.data.length - 1);
  }

  public pop(): PairEntry | undefined {
    if (this.data.length === 0) return undefined;

    const root = this.data[0];
    const last = this.data.pop()!;

    if (this.data.length > 0) {
      this.data[0] = last;
      this.siftDown(0);
    }

    return root;
  }

  private siftUp(index: number): void {
    let i = index;
    while (i > 0) {
      const parent = (i - 1) >> 1;
      if (this.less(i, parent)) {
        this.swap(i, parent);
        i = parent;
      } else {
        break;
      }
    }
  }

  private siftDown(index: number): void {
    let i = index;
    const n = this.data.length;

    while (true) {
      let best = i;
      const left = i * 2 + 1;
      const right = i * 2 + 2;

      if (left < n && this.less(left, best)) best = left;
      if (right < n && this.less(right, best)) best = right;

      if (best === i) break;
      this.swap(i, best);
      i = best;
    }
  }

  private less(i: number, j: number): boolean {
    const a = this.data[i];
    const b = this.data[j];

    if (a.sum !== b.sum) return a.sum < b.sum;
    return a.left < b.left;
  }

  private swap(i: number, j: number): void {
    const tmp = this.data[i];
    this.data[i] = this.data[j];
    this.data[j] = tmp;
  }
}

class Solution {
  minimumPairRemoval(nums: number[]): number {
    const n = nums.length;
    if (n <= 1) return 0;

    const value = nums.slice();
    const prev = new Array<number>(n);
    const next = new Array<number>(n);
    const alive = new Array<boolean>(n).fill(true);
    const pairGen = new Array<number>(n).fill(0);

    for (let i = 0; i < n; i++) {
      prev[i] = i - 1;
      next[i] = i + 1 < n ? i + 1 : -1;
    }

    let descents = 0;
    for (let i = 0; i + 1 < n; i++) {
      if (value[i] > value[i + 1]) descents++;
    }

    const heap = new MinHeap();

    const addPair = (left: number): void => {
      if (left < 0 || !alive[left]) return;
      const right = next[left];
      if (right === -1 || !alive[right]) return;

      pairGen[left]++;
      heap.push({
        sum: value[left] + value[right],
        left,
        gen: pairGen[left],
      });
    };

    const popValidPair = (): PairEntry | undefined => {
      while (true) {
        const top = heap.pop();
        if (!top) return undefined;

        const left = top.left;
        if (!alive[left]) continue;
        if (pairGen[left] !== top.gen) continue;

        const right = next[left];
        if (right === -1 || !alive[right]) continue;

        return top;
      }
    };

    for (let i = 0; i + 1 < n; i++) addPair(i);

    let operations = 0;

    while (descents > 0) {
      const pair = popValidPair();
      if (!pair) break;

      const left = pair.left;
      const right = next[left];
      if (right === -1) break;

      const p = prev[left];
      const r = next[right];

      if (p !== -1 && value[p] > value[left]) descents--;
      if (value[left] > value[right]) descents--;
      if (r !== -1 && value[right] > value[r]) descents--;

      value[left] += value[right];
      alive[right] = false;
      next[left] = r;
      if (r !== -1) prev[r] = left;

      if (p !== -1 && value[p] > value[left]) descents++;
      if (r !== -1 && value[left] > value[r]) descents++;

      addPair(p);
      addPair(left);
      operations++;
    }

    return operations;
  }
}

export default Solution;
