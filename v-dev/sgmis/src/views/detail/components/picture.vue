<script>
export default {
    props: {
        urls: Array
    }
}
</script>
<template>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">鸽子图片</a-divider>
    <div class="picture">
        <a-image-preview-group v-if="urls.length" infinite>
            <a-space>
                <div v-for="item in urls">
                    <a-image width="200" height="200" fit="contain" :src="item"/>
                </div>
            </a-space>
        </a-image-preview-group>
        <a-empty v-if="!urls.length" description="暂无图片"></a-empty>
    </div>
</template>

<style scoped>
.picture {
    text-align: center;
    padding: 40px;
}
</style>