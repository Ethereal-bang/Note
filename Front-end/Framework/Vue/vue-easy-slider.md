# vue-easy-slider

[vue-easy-slider](https://link.juejin.cn/?target=https%3A%2F%2Fgithub.com%2Fshhdgit%2Fvue-easy-slider) 是一个简单的 Vue 滑块组件，可与鼠标和触摸屏一起使用。可自定义，并带有动画效果



# 安装

```shell
npm i -S vue-easy-slider
```



# 使用

```vue
<template>
  <Slider
    v-model="sliderValue"
    animation="fade"
    :duration="5000"
    :speed="2000"
  >
    <SliderItem
      v-for="(i, index) in list"
      :key="index"
      @click="changeIndex(1);"
      :style="i"
    >
      <p style="line-height: 280px; font-size: 5rem; text-align: center;">
        Page{{ index + 1 }}
      </p>
    </SliderItem>
  </Slider>
</template>

<script>
import {Slider, SliderItem} from "vue-easy-slider";

export default {
  name: "Carousel",
  components: {
    Slider,
    SliderItem,
  },
  data() {
    return {
      list: [
        {
          backgroundColor: "#3f51b5",
          width: "100%",
          height: "100%"
        },
        {
          backgroundColor: "#eee",
          width: "100%",
          height: "100%"
        },
        {
          backgroundColor: "#f44336",
          width: "100%",
          height: "100%"
        },
      ],
      sliderValue: 1
    };
  },
  methods: {
    changeIndex(index) {
      this.sliderValue = index;
    }
  }
};
</script>

<style scoped>
</style>
```



## Slider 组件配置项

+ `:speed`：切换速度

+ animation: "fade"——渐变
