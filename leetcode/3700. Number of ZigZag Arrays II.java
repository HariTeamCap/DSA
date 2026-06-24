class Solution {
    static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        if (n == 1) return m;

        // Initial state at length=2
        // up[i] = i (# ways ending at i going up = # smaller predecessors)
        // down[i] = m-1-i (# ways ending at i going down)
        long[] state = new long[2 * m];
        for (int i = 0; i < m; i++) {
            state[i] = i;           // up[i]
            state[m + i] = m - 1 - i; // down[i]
        }

        if (n == 2) {
            long ans = 0;
            for (long v : state) ans = (ans + v) % MOD;
            return (int) ans;
        }

        // Build 2m x 2m transition matrix
        int sz = 2 * m;
        long[][] T = new long[sz][sz];
        for (int y = 0; y < m; y++) {
            // newUp[y] = sum of down[0..y-1]
            for (int k = 0; k < y; k++) {
                T[y][m + k] = 1;
            }
            // newDown[y] = sum of up[y+1..m-1]
            for (int k = y + 1; k < m; k++) {
                T[m + y][k] = 1;
            }
        }

        // We need T^(n-2) applied to state
        long[][] Tpow = matPow(T, n - 2, sz);
        long[] result = matVec(Tpow, state, sz);

        long ans = 0;
        for (long v : result) ans = (ans + v) % MOD;
        return (int) ans;
    }

    // Matrix multiplication mod MOD
    long[][] matMul(long[][] A, long[][] B, int sz) {
        long[][] C = new long[sz][sz];
        for (int i = 0; i < sz; i++) {
            for (int k = 0; k < sz; k++) {
                if (A[i][k] == 0) continue; // skip zeros for speed
                for (int j = 0; j < sz; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    // Matrix exponentiation: A^p
    long[][] matPow(long[][] A, long p, int sz) {
        // Identity matrix
        long[][] result = new long[sz][sz];
        for (int i = 0; i < sz; i++) result[i][i] = 1;

        while (p > 0) {
            if ((p & 1) == 1) result = matMul(result, A, sz);
            A = matMul(A, A, sz);
            p >>= 1;
        }
        return result;
    }

    // Matrix-vector multiply
    long[] matVec(long[][] A, long[] v, int sz) {
        long[] out = new long[sz];
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                out[i] = (out[i] + A[i][j] * v[j]) % MOD;
            }
        }
        return out;
    }
}


//Time:O(n × m)
//SpaceO(m)
//With m ≤ 75, sz = 2m ≤ 150, so each matrix multiply is 150³ ≈ 3.4M ops, and we do log₂(10⁹) ≈ 30 of them → ~100M ops total, well within limits.
//The key trick: whenever you have a linear recurrence with large n, matrix exponentiation brings it from O(n) to O(log n).


//Approach



// ### Problem Restatement
// Count arrays of length `n` with values in `[l, r]` where **no 3 consecutive elements are monotone** (strictly increasing or decreasing). Since only the range size matters, let `m = r - l + 1`.

// ---

// ### Step 1 — Classify each position by its "direction"

// At any position `i`, the array either:
// - **went up** to reach `i` (previous element < current)
// - **went down** to reach `i` (previous element > current)

// So define:
// - `up[v]` = number of valid arrays ending at value `v` where the **last step was upward**
// - `down[v]` = number of valid arrays ending at value `v` where the **last step was downward**

// ---

// ### Step 2 — Understand the ZigZag constraint

// The constraint "no 3 consecutive strictly increasing/decreasing" means:

// > **After going up, the next step must go down. After going down, the next step must go up.**

// So:
// - If last step was **up** to `y` → next value `z` must satisfy `z < y` → contributes to `down[z]`
// - If last step was **down** to `y` → next value `z` must satisfy `z > y` → contributes to `up[z]`

// This gives the recurrence:
// ```
// newUp[y]   = sum of down[x] for all x < y   (came down from somewhere, now go up to y)
// newDown[y] = sum of up[x]   for all x > y   (came up from somewhere, now go down to y)
// ```

// ---

// ### Step 3 — Base case (length = 2)

// For a 2-element array `[a, b]`:
// - `up[b]`   = number of valid `a < b` = `b` choices (values `0, 1, ..., b-1`)
// - `down[b]` = number of valid `a > b` = `m-1-b` choices

// ```
// up[i]   = i
// down[i] = m - 1 - i
// ```

// ---

// ### Step 4 — The naive approach is too slow

// Applying the recurrence step-by-step from length `2` to `n` costs **O(n × m)**.  
// With `n` up to `10⁹`, this is way too slow → **TLE**.

// ---

// ### Step 5 — Key observation: it's a linear recurrence!

// The state is a vector of size `2m`:
// ```
// [up[0], up[1], ..., up[m-1], down[0], down[1], ..., down[m-1]]
// ```

// Each step is just a **matrix-vector multiplication**:

// ```
// new_state = T × old_state
// ```

// Where transition matrix `T` (size `2m × 2m`) encodes:
// ```
// newUp[y]   depends on down[0..y-1]   → 1s at columns m, m+1, ..., m+y-1
// newDown[y] depends on up[y+1..m-1]   → 1s at columns y+1, ..., m-1
// ```

// ---

// ### Step 6 — Matrix Exponentiation

// Instead of applying `T` one step at a time `(n-2)` times, compute:

// ```
// T^(n-2) × initial_state
// ```

// Using **repeated squaring**, this takes only `O(log n)` matrix multiplications.

// ```
// T^1   → T
// T^2   → T × T
// T^4   → T^2 × T^2
// T^8   → T^4 × T^4
// ...
// ```

// Each multiplication costs `O((2m)³) = O(m³)`.

// ---

// ### Full Complexity

// ```
// O(m³ × log n)
// ```

// With `m ≤ 75` and `n ≤ 10⁹`:
// - Matrix size = `150 × 150`
// - Multiplications = `~30` (log₂ of 10⁹)
// - Total ops ≈ `150³ × 30 ≈ 100M` ✅

// ---

// ### Visual Summary

// ```
// Length 2          Length 3          Length 4              Length n
// [up, down]  →×T→  [up, down]  →×T→  [up, down]  → ... →  [up, down]
//                                                               ↑
//                                               computed as T^(n-2) × base
// ```

// > **Core insight:** Any time you have a **linear recurrence** with **huge n**, represent one step as a matrix and use **matrix exponentiation** to jump directly to step n in O(log n) time.