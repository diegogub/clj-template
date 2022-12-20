#!/usr/bin/env fish
set SESSION ##name##

if tmux has-session -t$SESSION
    tmux attach-session -t$SESSION
else
    tmux new-session -d -t$SESSION

    echo "new repl"
    tmux new-window -t$SESSION -nrepl
    tmux send-keys -t"$SESSION:repl" "clj -M:repl/conjure" Enter

    sleep 0.5

    tmux send-keys -t"$SESSION:1" "vim build.clj" Enter
    tmux attach-session -t$SESSION
end
