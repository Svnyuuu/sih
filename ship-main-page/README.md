nothing but to test

	上传分支
git checkout pitohui
git branch --set-upstream-to=origin/pitohui pitohui  # 如果是第一次推送，运行这行
git pull origin pitohui
git add .
git commit -m "你的提交信息"
git push origin pitohui

	gittee合并冲突
git pull origin pitohui --allow-unrelated-histories
git add .
git commit -m "Resolved merge conflicts"
git push origin pitohui