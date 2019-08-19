import Diff from "./diff.min.js";
export default {
    diff: (oldStr, newStr) => {
        // debugger
        let result = Diff.diffLines(oldStr, newStr).map(elm => {
            if (!elm.added) {
                delete elm.value;
                delete elm.added;
            }
            return elm;
        });
        return result;
    },
    applyDiff: (oldStr, diff) => {
        let lines = oldStr.split("\n");
        let li = 0;
        for (let di = 0; di < diff.length; di++) {
            let d = diff[di];
            if (d.added) {
                let linesAdded = d.value.split("\n").slice(0, d.count);
                lines.splice(li, 0, ...linesAdded);
                li += d.count;
            } else if (d.removed) {
                lines.splice(li, d.count);
            } else {
                li += d.count;
            }
        }
        let newStr = lines.join("\n");
        return newStr;
    },
    checkSyncReady: (syncItems, inputCount) => {
        let count = 0;
        syncItems.forEach(item => {
            // content变化
            if (item.diff) {
                item.diff.forEach(d => {
                    // 是增或删
                    if (d.removed || d.added) {
                        count += d.count;
                    }
                });
            } else {
                // title、noteId变化
                count++;
            }
        });
        console.log("checkSyncReady count", count);
        return count > inputCount;
    }
};
