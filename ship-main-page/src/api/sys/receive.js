import axios from 'axios';
const baseURL = "/api";
const instance = axios.create({baseURL})


//获取所有消息
export async function listReceive(params) {
    return await instance.get('/sys/receive', {params:params})
        .then(res => {
            return res.data;
        }).catch(err => {
            console.log(err);
        });
}

//移入回收站d
export async function HandleDelete(id) {
   console.log('id='+id);
    return await instance.delete('/sys/delete?id='+id)
        .then(res => {
            return res.data.message;
        }).catch(err => {
           console.log(err);
        });
}
//批量移入回收站
export async function BaDelete(ids) {
    console.log('ids='+ids);
    return await instance.delete('/sys/batchDelete?ids='+ids)
        .then(res => {
            return res.data.message;
        }).catch(err => {
           console.log(err);
        });
}
//收藏 handleFavorite
export async function handleFavorite(id,favorite) {
    return await instance.get('/sys/receive/collectUpdate?id='+id+'&favorite='+favorite)
        .then(res => {
            return res.data;
        }).catch(err => {
           console.log(err);
        });
}



//永久删除消息
export async function HandleForeverDelete(id) {
   console.log('id='+id);
    return await instance.delete('/sys/deleteForever?id='+id)
        .then(res => {
            return res.data.message;
        }).catch(err => {
           console.log(err);
        });
}

//获取所有收藏消息
export async function listFavorite(params) {
    return await instance.get('/sys/favorite', {params:params})
        .then(res => {
            return res.data;
        }).catch(err => {
            console.log(err);
        });
}


//获取所有草稿消息
export async function listDraft(params) {
    return await instance.get('/sys/draft', {params:params})
        .then(res => {
            return res.data;
        }).catch(err => {
            console.log(err);
        });
}

//获取下载地址
export async function getUrl(url) {
    console.log('url='+url);
    return await instance.get(url)
        .then(res => {
            return res.data.url;
        }).catch(err => {
            console.log(err);
        });
}

//

